package com.bonc.rdpe.security.repository;

import javax.servlet.http.HttpServletRequest;

import com.bonc.rdpe.security.util.Constant;

/**
 * @author 作者: 吕一凡 
 * @date 2017年1月9日 下午9:13:19 
 * @version 版本: 1.0
 * 权限资源库接口
 */
public interface ISecurityRepository {
	
	/**
	 * 根据类型获取资源
	 * @return
	 */
	public Object getResourceByType(Constant.ResourceType resourceType,HttpServletRequest request);
	
	/**
	 * 根据类型存放资源
	 * @return
	 */
	public void putResourceByType(Constant.ResourceType resourceType,HttpServletRequest request,Object object);
}
