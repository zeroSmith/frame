package com.bonc.rdpe.security.handle;

import java.io.IOException;

import com.bonc.rdpe.security.exception.LoginTimeoutException;
import com.bonc.rdpe.security.wrap.SecurityRequestWrap;
import com.bonc.rdpe.util.RequestUtil;

/**
 * @author 作者: 吕一凡 
 * @date 2017年1月9日 下午9:24:20 
 * @version 版本: 1.0
 */
public class LoginCheckHandle implements ISecurityHandle{
	
	private String loginPageUri;
	
	@Override
	public void successHandle(SecurityRequestWrap securityRequestWrap) {
		// TODO Auto-generated method stub
	}

	@Override
	public void failHandle(SecurityRequestWrap securityRequestWrap) throws IOException,Exception {
		if(RequestUtil.isAjaxReq(securityRequestWrap.getReq())){//如果是ajax请求
			throw new LoginTimeoutException("用户登录超时！");
		}else{//不是ajax请求
			securityRequestWrap.getResp()
		      .sendRedirect(securityRequestWrap.getReq().getContextPath()+loginPageUri);
		}
	}

	public String getLoginPageUri() {
		return loginPageUri;
	}

	public void setLoginPageUri(String loginPageUri) {
		this.loginPageUri = loginPageUri;
	}
}
