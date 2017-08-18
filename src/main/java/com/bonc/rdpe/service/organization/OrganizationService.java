package com.bonc.rdpe.service.organization;

import java.util.List;
import java.util.Map;

import com.bonc.rdpe.entity.orgnization.Orgnization;

/** 
 * 组织机构service接口，此接口内包含对组织机构的一些常用操作的封装
 * @author qxl
 * @date 2017年1月10日 上午11:18:30 
 * @version 1.0.0
 */
public interface OrganizationService {

	/**
	 * 查询所有的组织机构
	 * @return
	 */
	public List<Map<String,String>> selectAll();
	
	/**
	 * 查询树状所有的组织机构
	 * @return
	 */
	public List<Orgnization> orgListTree();
	
	/**
	 * 根据组织机构Id查询其所有一级孩子节点
	 * @param orgId 组织机构Id
	 * @param tenantId 租户Id
	 * @return
	 */
	public List<Map<String,String>> selectByOrgId(String orgId,String tenantId);
	
	/**
	 * 根据组织机构Id查询其所有孩子节点
	 * @param orgId 组织机构Id
	 * @param path 组织机构路径
	 * @param tenantId 租户Id
	 * @return
	 */
	public List<Map<String,String>> selectAllByOrgId(String orgId,String path,String tenantId);
	
	/**
	 * 根据用户Id查询其所属的组织机构的所有一级子节点
	 * @param userId 用户id
	 * @param tenantId 租户Id
	 * @return
	 */
	public List<Map<String,String>> selectByUserId(String userId,String tenantId);
	
	/**
	 * 根据用户Id查询其所属的组织机构的所有孩子节点
	 * @param userId 用户id
	 * @param tenantId 租户Id
	 * @return
	 */
	public List<Map<String,String>> selectAllByUserId(String userId,String tenantId);
	
	/**
	 * 根据组织机构Id删除其所有子节点
	 * @param orgId 组织机构id
	 * @param path 组织机构路径
	 * @return
	 */
	public void deleteByOrgId(String orgId,String path)throws Exception;
	
	/**
	 * 添加组织结构节点
	 * @param orgnization
	 * @author "YanS"
	 */
	public int addOrg(Orgnization orgnization);
	
	/**
	 * 修改组织结构节点
	 * @param orgnization
	 * @author "YanS"
	 */
	public int updateOrg(Orgnization orgnization);
	
	
}
