package com.bonc.rdpe.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 作者: jxw
 * @date 创建时间: 2017年1月17日 下午2:51:06
 * @version 版本: 1.0
 */
public class RequestUtil {

	/**
	 * 判断请求是否为ajax请求 ,是返回true 不是返回false
	 * @param request
	 * @return
	 */
	public static boolean isAjaxReq(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}
}
