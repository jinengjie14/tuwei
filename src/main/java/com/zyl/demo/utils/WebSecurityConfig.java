package com.zyl.demo.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 登录拦截器
 * @author Zyl
 *
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter{
	
	public final static String USERID = "sessionid";
	public final static String USERACCOUNT = "sessionaccount";
    @Bean
    public SecurityInterceptor getSecurityInterceptor (){
		return new SecurityInterceptor();
    }
    
    public void addInterceptors(InterceptorRegistry registry){
    	InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
    	
    	// 排除配置
    	addInterceptor.excludePathPatterns("/login");
    	addInterceptor.excludePathPatterns("/register");
    	
		// 拦截配置
		addInterceptor.addPathPatterns("/**");
    }
    class SecurityInterceptor extends HandlerInterceptorAdapter{
    	
    	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)
    			throws Exception {
    				HttpSession session = request.getSession();
    				if(session.getAttribute(USERID) != null || session.getAttribute(USERACCOUNT) != null)
    				return true;

    				// 跳转登录
    				String url = "/login";
    				response.sendRedirect(url); 
    				return false;
    			}
    	}
}
