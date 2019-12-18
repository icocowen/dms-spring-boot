package com.iwne.dorm.info.mangement.filter;

import com.alibaba.fastjson.JSON;
import com.iwne.dorm.info.mangement.controller.ValidateCodeController;
import com.iwne.dorm.info.mangement.exception.ValidateCodeException;
import com.iwne.dorm.info.mangement.service.RequestWrapper;
import com.iwne.dorm.info.mangement.validate.ImageCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String url = httpServletRequest.getRequestURL().toString();

        RequestWrapper requestWrapper = null;

        String path = "";
        try{
            path = url.substring(url.lastIndexOf("/"));
        }catch (Exception e) {
            path = url;
        }

        if (path.equals("/login") && httpServletRequest.getMethod().equals("POST")) {
            try {
                requestWrapper = new RequestWrapper(httpServletRequest);
                validate(new ServletWebRequest(requestWrapper));

            }catch (AuthenticationException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        if (requestWrapper != null) {
            filterChain.doFilter(requestWrapper,httpServletResponse);   //如果不是登录请求，直接调用后面的过滤器链
        }else {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException, IOException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
//        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"imageCode");
        String body = StreamUtils.copyToString(((RequestWrapper)request.getRequest()).getInputStream(), Charset.forName("UTF-8"));

        String codeInRequest = JSON.parseObject(body).getString("imageCode");

        if(!StringUtils.hasText(codeInRequest)){
            throw new ValidateCodeException("验证码的值不能为空！");
        }
        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在！");
        }
        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期！");
        }
        if(!codeInSession.getCode().equals(codeInRequest)){
            throw new ValidateCodeException("验证码不正确！");
        }
        sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
    }
}