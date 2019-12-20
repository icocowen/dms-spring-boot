package com.iwne.dorm.info.mangement.configuration;

import java.util.Arrays;

import com.iwne.dorm.info.mangement.dao.AdaministratorMapper;
import com.iwne.dorm.info.mangement.filter.OptionsRequestFilter;
import com.iwne.dorm.info.mangement.filter.ValidateCodeFilter;
import com.iwne.dorm.info.mangement.service.JwtAuthenticationProvider;
import com.iwne.dorm.info.mangement.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired(required = false)
    AdaministratorMapper adaministratorMapper;


	protected void configure(HttpSecurity http) throws Exception {
		ValidateCodeFilter codeFilter = new ValidateCodeFilter();
		codeFilter.setAuthenticationFailureHandler(new CodeAuthenctiationFailureHandler());
		http.authorizeRequests()
		        .antMatchers("/image/**","/code/img").permitAll()
		        .anyRequest().authenticated()
		        .and()
		    .csrf().disable()
		    .formLogin().disable()
		    .sessionManagement().disable()
		    .cors()
		    .and()
		    .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
		    		new Header("Access-control-Allow-Origin","*"),
		    		new Header("Access-Control-Expose-Headers","Authorization"))))
		    .and()
			.addFilterAfter(codeFilter, CorsFilter.class)
		    .addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)
		    .apply(new JsonLoginConfigurer<>()).loginSuccessHandler(jsonLoginSuccessHandler())
		    .and()
		    .apply(new JwtLoginConfigurer<>()).tokenValidSuccessHandler(jwtRefreshSuccessHandler()).permissiveRequestUrls("/logout")
		    .and()
		    .logout()
//		        .logoutUrl("/logout")   //默认就是"/logout"
		        .addLogoutHandler(tokenClearLogoutHandler())
		        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
		    .and()
		    .sessionManagement().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider()).authenticationProvider(jwtAuthenticationProvider());
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	@Bean("jwtAuthenticationProvider")
	protected AuthenticationProvider jwtAuthenticationProvider() {
		return new JwtAuthenticationProvider(jwtUserService());
	}
	
	@Bean("daoAuthenticationProvider")
	protected AuthenticationProvider daoAuthenticationProvider() throws Exception{
		//这里会默认使用BCryptPasswordEncoder比对加密后的密码，注意要跟createUser时保持一致
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(userDetailsService());
		return daoProvider;
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return new JwtUserService(adaministratorMapper);
	}
	
	@Bean("jwtUserService")
	protected JwtUserService jwtUserService() {
		return new JwtUserService(adaministratorMapper);
	}
	
	@Bean
	protected JsonLoginSuccessHandler jsonLoginSuccessHandler() {
		return new JsonLoginSuccessHandler(jwtUserService());
	}
	
	@Bean
	protected JwtRefreshSuccessHandler jwtRefreshSuccessHandler() {
		return new JwtRefreshSuccessHandler(jwtUserService());
	}
	
	@Bean
	protected TokenClearLogoutHandler tokenClearLogoutHandler() {
		return new TokenClearLogoutHandler(jwtUserService());
	}
	
	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","HEAD", "OPTION","PUT"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.addExposedHeader("Authorization");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}


//    @Bean
//    public FormContentFilter httpPutFormContentFilter() {
//        return new FormContentFilter();
//    }

	@Bean
	public FilterRegistrationBean<FormContentFilter> testFilterRegistration2() {
		FilterRegistrationBean<FormContentFilter> registration = new FilterRegistrationBean<FormContentFilter>();
		registration.setFilter(new FormContentFilter());//添加过滤器
		registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
		registration.setName("FormContentFilter");//设置优先级
		registration.setOrder(2);//设置优先级
		return registration;
	}

}
