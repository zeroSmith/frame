package com.bonc.rdpe.entity.log;

import java.util.Date;

/** 
 * @author 作者: jxw 
 * @date 创建时间: 2017年1月31日 下午6:20:44 
 * @version 版本: 1.0 
*/
public class UserLoginLog {

	private String userId;
	
	private Date LoginDate;
	
	private String loginIp;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLoginDate() {
		return LoginDate;
	}

	public void setLoginDate(Date loginDate) {
		LoginDate = loginDate;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
}

