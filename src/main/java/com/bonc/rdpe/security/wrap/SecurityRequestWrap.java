package com.bonc.rdpe.security.wrap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SecurityRequestWrap {
	
	private HttpServletRequest req;
	
	private HttpServletResponse resp;
	
	public SecurityRequestWrap(HttpServletRequest req, HttpServletResponse resp) {
		super();
		this.req = req;
		this.resp = resp;
	}
	
	
	public HttpServletRequest getReq(){
		return this.req;
	}
	
	

	public HttpServletResponse getResp() {
		return resp;
	}	

	public HttpSession getSession(){
		return req.getSession();
	}	
	
}
