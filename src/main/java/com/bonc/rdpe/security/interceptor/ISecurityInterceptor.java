package com.bonc.rdpe.security.interceptor;

import com.bonc.rdpe.security.wrap.SecurityRequestWrap;

/**
 * @author 作者: 吕一凡 
 * @date 2017年1月9日 下午9:16:52 
 * @version 版本: 1.0
 * 拦截器父接口
 */
public interface ISecurityInterceptor {
	
	/**
	 * 拦截器方法
	 * @return
	 */
	public void doInterceptor(SecurityRequestWrap securityRequestWrap)throws Exception;
	
	public void setChildInterceptor(ISecurityInterceptor securityInterceptor);
	
	public void doNext(SecurityRequestWrap securityRequestWrap)throws Exception;
}
