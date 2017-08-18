package com.bonc.rdpe.service.resources;

import java.util.List;

import com.bonc.rdpe.entity.resources.Resources;

/**
 * @author 作者: 吕一凡 
 * @date 2017年1月11日 下午3:52:04 
 * @version 版本: 1.0
 */
public interface ResourceService {
	
	
	public List<Resources> selectResourcesList();
	/**
	 * 查询所有资源记录，is_Auth字段为1表示该用户有权限访问，-1为无权限访问
	 * @return
	 */
	public List<Resources> selectAll(String userId);
	
	/**
	 * 查询所有按钮资源，is_Auth字段为1表示该用户有权限访问，-1为无权限访问
	 * @param userId
	 * @return
	 */
	public List<Resources> selectAllButton(String userId);
	
	/**
	 * 根据资源Id查询该条记录
	 * @param resourceId 资源Id
	 * @return
	 */
	public Resources selectByResourceId(String resourceId);
	
	/**
	 * 更新资源信息
	 * @param resources 资源对象
	 * @return
	 */
	public int update(Resources resources);
	
	/**
	 * 新增资源信息
	 * @param resources 资源对象
	 * @return
	 */
	public int insert(Resources resources);
	
	/**
	 * 根据资源Id删除该条信息
	 * @param resourceId 资源id
	 * @return
	 */
	public int deleteByResourceId(String resourceId)throws Exception;
	
	/**
	 * 加载资源管理资源信息树
	 * @return
	 */
	public List<Resources> resourcesListTree();
	
	/**
	 * 加载用户菜单
	 * @return
	 */
	public List<Resources> userMenuTree(String userId);
}
