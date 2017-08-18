package com.bonc.rdpe.util;

import com.bonc.rdpe.vo.ResponseResult;

/**
 * 执行结果提示工具类
 * 
 * @author qxl
 * @date 2016年10月19日 下午3:46:54
 * @version 1.0.0
 */
public class ResultUtil {
	
	/**
	 * 创建执行正确的返回结果信息
	 * @return com.bonc.framework.web.vo.ResponseResult
	 */
	public static ResponseResult createSuccessInfo(){
		ResponseResult result = new ResponseResult();
		result.setStatus(ResponseResult.SUCCESS_STAUS);
		return result;
	}
	/**
	 * 创建执行正确的返回结果信息
	 * @param msg 提示信息
	 * @return com.bonc.framework.web.vo.ResponseResult
	 */
	public static ResponseResult createSuccessInfo(String msg){
		ResponseResult result = createSuccessInfo();
		result.setMsg(msg);
		return result;
	}
	/**
	 * 创建执行正确的返回结果信息
	 * @param msg 提示信息
	 * @param data 返回数据
	 * @return com.bonc.framework.web.vo.ResponseResult
	 */
	public static ResponseResult createSuccessInfo(String msg,Object data){
		ResponseResult result = createSuccessInfo();
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	
	/**
	 * 创建执行异常的返回结果信息
	 * @return com.bonc.framework.web.vo.ResponseResult
	 */
	public static ResponseResult createFailInfo(){
		ResponseResult result = new ResponseResult();
		result.setStatus(ResponseResult.ERROR_STAUS);
		return result;
	}
	/**
	 * 创建执行异常的返回结果信息
	 * @param msg 提示信息
	 * @return com.bonc.framework.web.vo.ResponseResult
	 */
	public static ResponseResult createFailInfo(String msg){
		ResponseResult result = createFailInfo();
		result.setMsg(msg);
		return result;
	}
	/**
	 * 创建执行异常的返回结果信息
	 * @param msg 提示信息
	 * @param data 返回数据
	 * @return com.bonc.framework.web.vo.ResponseResult
	 */
	public static ResponseResult createFailInfo(String msg,Object data){
		ResponseResult result = createFailInfo();
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
}
