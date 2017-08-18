package com.bonc.rdpe.security.authication;

import javax.servlet.http.HttpServletRequest;

import com.bonc.rdpe.exception.LoginException;

/**
 * @author 作者: 吕一凡 
 * @date 2017年1月9日 下午9:00:22 
 * @version 版本: 1.0
 * 用户登录信息校验接口
 */
public interface IAuthication {
	
	/**
	 * 用户登录信息校验
	 * @return userId  不是 loginId
	 * @throws Exception 
	 */
	public String authCheck(HttpServletRequest request) throws LoginException;
	
	/**
	 * 将用户信息放入Session
	 * @param request
	 * @param userId
	 */
	public void putUserResource(HttpServletRequest request,String userId);
	
	/**
	 * 将用户菜单资源放入Session
	 * @param request
	 * @param userId
	 */
	public void putUserMenuResource(HttpServletRequest request,String userId);
	
	/**
	 * 将全部菜单资源放入Session,默认处理成/xx/**
	 * @param request
	 * is_Auth字段为1则用户有访问权限，-1为用户无访问权限
	 */
	public void putMenuResource(HttpServletRequest request,String userId);
	
	/**
	 * 将全部按钮资源放入Session
	 * @param request
	 * @param userId
	 * is_Auth字段为1则用户有访问权限，-1为用户无访问权限
	 */
	public void putButtonResource(HttpServletRequest request,String userId);
	
	/**
	 * 将Session中所有信息清除
	 * @param request
	 */
	public void invalidateSessionResource(HttpServletRequest request);
	
	
	/**
	 * 修改用户密码
	 * @param request
	 */
	public void updateUserPasswd(HttpServletRequest request,String newPasswd);
	
	/**
	 * 记录用户登录日志
	 * @param request userId
	 */
	public void doUserLoginLog(HttpServletRequest request,String userId);
	
	/**
	 * 记录系统登录人数
	 * @param request
	 * @param userId
	 */
	public void countOnlineUser(HttpServletRequest request,String userId);
	
	/**
	 * 记录系统参数表的内容
	 * @param request
	 * @param userId
	 */
	public void putSysDict(HttpServletRequest request,String userId);
}
