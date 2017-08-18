package com.bonc.rdpe.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * @author 作者: jxw 
 * @date 创建时间: 2017年1月17日 下午2:51:21 
 * @version 版本: 1.0 
*/
public class ResponseUtil {
	
	private static Log log = LogFactory.getLog(ResponseUtil.class);
   
	//异步请求返回
	public static  void write(HttpServletResponse response,Object object){
		try{
			response.setContentType("text/html;charset=utf-8");
			log.debug("send message is : "+object.toString());
			response.getWriter().write(object.toString());
			response.getWriter().flush();
	    }catch(IOException e){
	    	e.printStackTrace();
	    }finally{
	    	try {
	    		response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
}

