package com.iwne.dorm.info.mangement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iwne.dorm.info.mangement.dao.AdaministratorMapper;
import com.iwne.dorm.info.mangement.model.TabAdministrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.util.StringUtils;


public class JwtUserService implements UserDetailsService{
	
	private PasswordEncoder passwordEncoder;


	private AdaministratorMapper adaministratorMapper;


	public JwtUserService(AdaministratorMapper adaministratorMapper) {
		this.adaministratorMapper = adaministratorMapper;
		this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();  //默认使用 bcrypt， strength=10 
	}

	public UserDetails getUserLoginInfo(String username) {
		String salt = adaministratorMapper.findByJobNum(username).getSalt();
		if (StringUtils.isEmpty(salt)) {
			throw new NonceExpiredException("Token expires");
		}
    	UserDetails user = loadUserByUsername(username);
    	//将salt放到password字段返回
    	return User.builder().username(user.getUsername()).password(salt).authorities(user.getAuthorities()).build();
	}
	
	public String saveUserLoginInfo(UserDetails user) {

		String salt = BCrypt.gensalt(20);

		adaministratorMapper.updateSalt(user.getUsername(), salt);

		Algorithm algorithm = Algorithm.HMAC256(salt);
		Date date = new Date(System.currentTimeMillis()+ 3*3600*1000);  //设置1小时后过期
        return JWT.create()
        		.withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return User.builder().username("iwen").password(passwordEncoder.encode("iwen")).roles("USER").build();
		TabAdministrator administrator = adaministratorMapper.findByJobNum(username);
		if (administrator == null) {
			throw new UsernameNotFoundException("User not found for name:"+ username);
		}
		return User.builder().username(administrator.getJobNum())
				.password(administrator.getPassword()).authorities("admin").build();
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority("admin"));
//		return new User(administrator.getJobNum(), administrator.getPassword()
//				, authorities);
	}
	
	public void createUser(String username, String password) {
		String encryptPwd = passwordEncoder.encode(password);

		/**
		 * @todo 保存用户名和加密后密码到数据库
		 */
	}
	
	public void deleteUserLoginInfo(String username) {

		/**
		 * @todo 清除数据库或者缓存中登录salt
		 */

		adaministratorMapper.removeSalt(username);
	}
}
