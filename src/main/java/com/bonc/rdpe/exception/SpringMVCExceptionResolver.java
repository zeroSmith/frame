package com.bonc.rdpe.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @author 作者: jxw 
 * @date 创建时间: 2017年2月10日 下午3:35:17 
 * @version 版本: 1.0 
 * 统一异常处理
*/
public class SpringMVCExceptionResolver implements HandlerExceptionResolver{
	
	private Logger log = Logger.getLogger(SpringMVCExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
		ex.printStackTrace();
		log.error("服务异常",ex);
        return null;
	}
}

