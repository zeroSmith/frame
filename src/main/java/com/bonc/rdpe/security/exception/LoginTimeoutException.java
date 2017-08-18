package com.bonc.rdpe.security.exception;
/** 
 * @author 作者: jxw 
 * @date 创建时间: 2017年1月23日 下午6:10:35 
 * @version 版本: 1.0 
*/
public class LoginTimeoutException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6816444031804138214L;
	
	public LoginTimeoutException(String msg){
		super(msg+",请重新登录！");
	}

}

