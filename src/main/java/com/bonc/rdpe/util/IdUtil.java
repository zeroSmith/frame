package com.bonc.rdpe.util;

import java.util.UUID;

/**
 * ID生成工具类
 * 
 * @author qxl
 * @date 2016年10月18日 上午9:06:17
 * @version 1.0.0
 */
public class IdUtil {


	/**
	 * 产生一个32位的UUID
	 * 
	 * @return
	 */
	public static String genUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").toUpperCase();
	}
	
}
