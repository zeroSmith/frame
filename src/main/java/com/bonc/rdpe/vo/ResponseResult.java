package com.bonc.rdpe.vo;

import java.io.Serializable;
/**
 * 	返回结果实体
 *  格式：{"status":0,"msg":"xxx","data":xxx}
 * 
 * @author qxl
 * @date 2016年10月11日 下午6:44:38
 * @version 1.0.0
 */
public class ResponseResult implements Serializable{
	private static final long serialVersionUID = 7860792049869292838L;
	
	public static final int SUCCESS_STAUS = 0;
	public static final int ERROR_STAUS = -1;
	
	private int status;//状态,0正确;其他错误
	private String msg;//消息
	private Object data;//传出去的数据
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
