package com.bonc.rdpe.security.repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bonc.rdpe.security.util.Constant.ResourceType;

/**
 * @author 作者: 吕一凡 
 * @date 2017年1月9日 下午9:14:23 
 * @version 版本: 1.0
 * Session权限资源库实现类
 */
public class SessionSecurityRepository implements ISecurityRepository{

	@Override
	public Object getResourceByType(ResourceType resourceType, HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getAttribute(resourceType.toString());
	}

	@Override
	public void putResourceByType(ResourceType resourceType, HttpServletRequest request, Object object) {
		HttpSession session = request.getSession();
		session.setAttribute(resourceType.toString(), object);
	}
}
