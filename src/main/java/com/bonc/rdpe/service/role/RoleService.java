package com.bonc.rdpe.service.role;

import java.util.List;
import java.util.Map;

import com.bonc.rdpe.entity.resources.Resources;
import com.bonc.rdpe.entity.role.Role;

/**
 * 角色管理service接口，此接口内包含对角色信息的一些常用操作的封装
 * @author 作者: 吕一凡 
 * @date 2017年1月17日 下午3:31:37 
 * @version 版本: 1.0
 */
public interface RoleService {
	
	/**
	 * 分页查询所有的角色
	 * @return
	 */
	public Map<String,Object> selectAll(String start,String length,Map<String,Object>paramMap);
	
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> selectAll();
	
	
	/**
	 * 根据角色Id查询该角色信息
	 * @param roleId 角色Id
	 * @return
	 */
	public Role selectByRoleId(String roleId);
	
	/**
	 * 根据角色Id查询该角色资源权限
	 * @param roleId 角色Id
	 * @return
	 */
	public List<Resources> selectResourcesByRoleId(String roleId);
	
	/**
	 * 根据角色Id删除资源权限
	 * @param roleId 角色Id
	 * @return
	 */
	public int deleteResourcesByRoleId(String roleId);
	
	/**
	 * 更新角色信息
	 * @param resource 角色对象
	 * @return
	 */
	public int update(Role resource);
	
	/**
	 * 新增角色信息
	 * @param resource 角色对象
	 * @return
	 */
	public int insert(Role resource);
	
	/**
	 * 根据角色Id删除该角色
	 * @param roleId 角色Id
	 * @return
	 */
	public int deleteByRoleId(String roleId);
	
	/**
	 * 插入角色与资源对应关系
	 * @param roleId 角色Id
	 * @return
	 */
	public void insertRoleResourceRef(List<Map<String,Object>> list,String roleId)throws Exception;
}
