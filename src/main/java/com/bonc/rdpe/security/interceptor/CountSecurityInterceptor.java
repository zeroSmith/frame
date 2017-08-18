package com.bonc.rdpe.security.interceptor;

import com.bonc.rdpe.security.wrap.SecurityRequestWrap;

/**
 * @author 作者: 吕一凡 
 * @date 2017年1月9日 下午9:18:46 
 * @version 版本: 1.0
 * 统计拦截器类
 */
public class CountSecurityInterceptor extends AbstractSecurityInterceptor{

	@Override
	public void doValidation(SecurityRequestWrap securityRequestWrap) throws Exception {
		// TODO Auto-generated method stub
		this.doNext(securityRequestWrap);
	}


}
