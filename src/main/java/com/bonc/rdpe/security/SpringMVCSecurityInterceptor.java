package com.bonc.rdpe.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bonc.rdpe.security.interceptor.manager.SecurityInterceptorManager;

/**
 * @author 作者: 吕一凡 
 * @date 2017年1月9日 下午9:23:11 
 * @version 版本: 1.0
 * Spring权限拦截器管理接口
 */
public class SpringMVCSecurityInterceptor implements HandlerInterceptor{
	
	private SecurityInterceptorManager securityInterceptorManager;

	public void setSecurityInterceptorManager(SecurityInterceptorManager securityInterceptorManager) {
		this.securityInterceptorManager = securityInterceptorManager;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		securityInterceptorManager.doInterceptor(request,response);
		if(response.isCommitted())
			return false;
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}


}
