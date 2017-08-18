package com.bonc.rdpe.security.interceptor;

import com.bonc.rdpe.security.util.Constant;
import com.bonc.rdpe.security.wrap.SecurityRequestWrap;

/**
 * @author 作者: 吕一凡 
 * @date 2017年1月9日 下午9:19:26 
 * @version 版本: 1.0
 * 验证是否登录拦截器类
 */
public class LoginCheckInterceptor extends AbstractSecurityInterceptor{

	@Override
	public void doValidation(SecurityRequestWrap securityRequestWrap) throws Exception {
		Object obj = this.securityRepository.getResourceByType(Constant.ResourceType.UserResource, securityRequestWrap.getReq());
	    if(obj==null){//未登录
	    	this.getSecurityHandle().failHandle(securityRequestWrap);
	    }else{//登录绕过该拦截器
	    	this.doNext(securityRequestWrap);
	    }
	}
}
