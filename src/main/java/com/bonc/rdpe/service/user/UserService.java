package com.bonc.rdpe.service.user;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import com.bonc.rdpe.entity.log.UserLoginLog;
import com.bonc.rdpe.entity.resources.Resources;
import com.bonc.rdpe.entity.user.User;

/**
 * 用户管理service接口，此接口内包含对用户信息的一些常用操作的封装
 * @author 作者: 吕一凡 
 * @date 2017年1月11日 上午11:23:58 
 * @version 版本: 1.0
 */
public interface UserService {
	
	
	
	/**
	 * 分页查询所有的用户
	 * @return
	 */
	public Map<String,Object> selectAll(String start,String length);
	
	/**
	 * 根据用户Id查询该用户信息
	 * @param userId 用户Id
	 * @return
	 */
	public User selectByUserId(String userId);
	
	/**
	 * 根据用户loginId查询该用户信息
	 * @param loginId 用户loginId
	 * @return
	 */
	public User selectByLoginId(String loginId);
	
	/**
	 * 更新用户信息
	 * @param user 用户对象
	 * @return
	 */
	public int update(User user);
	
	/**
	 * 新增用户信息
	 * @param user 用户对象
	 * @return
	 */
	public int insert(User user)throws Exception;
	
	/**
	 * 根据用户Id删除该用户
	 * @param userId 用户Id
	 * @return
	 */
	public int deleteByUserId(String userId)throws Exception;
	
	/**
	 * 根据用户Id查询用户菜单资源
	 * @param userId
	 * @return
	 */
	public List<Resources> selectUserResource(String userId);
	
	
	/**
	 * 记录用户登录日志
	 * @param UserLoginLog
	 * @return
	 * */
	public void doUserLoginLog(UserLoginLog log);
	
	/**
	 * 初始化密码
	 * @return
	 */
	public void initPasswd(String userId) throws NoSuchAlgorithmException;
	
	
	/**
	 * 分页查询所有的用户
	 * @return
	 */
	public Map<String, Object> getUserByCondition(Map<String,Object> paramMap,String start,String length);
	
	
	/**
	 * 用户授权
	 * @return
	 */
	public void userAuth(List<Map<String,Object>> list,String userId);
	
	/**
	 * 查询用户角色
	 * @return
	 */
	public List<Map<String,Object>> selectRoleByUser(String userId);
}
