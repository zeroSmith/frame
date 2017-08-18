package com.bonc.rdpe.security.interceptor.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bonc.rdpe.security.interceptor.ISecurityInterceptor;
import com.bonc.rdpe.security.wrap.SecurityRequestWrap;

/** 
 * @author 作者: jxw 
 * @date 创建时间: 2017年1月16日 下午7:04:48 
 * @version 版本: 1.0 
*/
public class SecurityInterceptorManager {
	
	private List<ISecurityInterceptor> securityInterceptors;
	
	private ISecurityInterceptor rootSecurityInterceptor;

	public SecurityInterceptorManager(List<ISecurityInterceptor> securityInterceptors) {
		super();
		this.securityInterceptors = securityInterceptors;
		ISecurityInterceptor tempSecurityInterceptor = null;
		for(ISecurityInterceptor securityInterceptor:securityInterceptors){
			if(tempSecurityInterceptor == null){
				tempSecurityInterceptor = securityInterceptor;
				rootSecurityInterceptor = tempSecurityInterceptor;
				continue;
			}
			tempSecurityInterceptor.setChildInterceptor(securityInterceptor);
			tempSecurityInterceptor = securityInterceptor;
		}
	}

	public void doInterceptor(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SecurityRequestWrap securityRequestWrap = new SecurityRequestWrap(request,response);
		if(rootSecurityInterceptor!=null)
		rootSecurityInterceptor.doInterceptor(securityRequestWrap);
	}

	public List<ISecurityInterceptor> getSecurityInterceptors() {
		return securityInterceptors;
	}

	public void setSecurityInterceptors(List<ISecurityInterceptor> securityInterceptors) {
		this.securityInterceptors = securityInterceptors;
	}
}

