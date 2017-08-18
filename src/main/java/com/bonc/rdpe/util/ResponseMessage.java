package com.bonc.rdpe.util;

/** 
 * @author 作者: jxw 
 * @date 创建时间: 2017年1月17日 下午2:55:11 
 * @version 版本: 1.0 
*/
public class ResponseMessage {
	
	private static final String SuccessCode = "1";
	
	private static final String FailCode = "-1";
	
	public ResponseMessage(){
		
	}
	
	public ResponseMessage(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	private String code;
	
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return JsonUtils.toJSONString(this);
	}

	public static ResponseMessage createSuccessMessage(String message){
		return new ResponseMessage(SuccessCode,message);
	}
	
	public static ResponseMessage createFailMessage(String message){
		return new ResponseMessage(FailCode,message);
	}
}

