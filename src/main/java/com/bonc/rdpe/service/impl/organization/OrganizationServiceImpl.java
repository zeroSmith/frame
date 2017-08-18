package com.bonc.rdpe.service.impl.organization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.rdpe.dao.DaoHelper;
import com.bonc.rdpe.entity.orgnization.Orgnization;
import com.bonc.rdpe.service.organization.OrganizationService;
import com.bonc.rdpe.util.StringUtil;

/** 
 * 组织机构service接口，此接口内包含对组织机构的一些常用操作的封装.
 * @author qxl
 * @date 2017年1月10日 上午11:21:49 
 * @version 1.0.0
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {
	
	@Resource
	private DaoHelper daoHelper;
	
	/**
	 * 组织机构相关操作Mapper的命名空间
	 */
	private final String orgMapperNameSpace = "com.bonc.base.organization.mapper.";

	@Override
	//查询所有的组织机构
	public List<Map<String, String>> selectAll() {
		return daoHelper.queryForList(orgMapperNameSpace+"selectAll");
	}

	@Override
	//根据组织机构Id查询其所有一级孩子节点
	public List<Map<String, String>> selectByOrgId(String orgId, String tenantId) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("orgId", orgId);
		param.put("tenantId", tenantId);
		return daoHelper.queryForList(orgMapperNameSpace+"selectByOrgId", param);
	}

	@Override
	//根据组织机构Id查询其所有孩子节点
	public List<Map<String, String>> selectAllByOrgId(String orgId,String path, String tenantId) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("orgId", orgId);
		param.put("path", path);
		param.put("tenantId", tenantId);
		return daoHelper.queryForList(orgMapperNameSpace+"selectAllByOrgId", param);
	}

	@Override
	//根据用户Id查询其所属的组织机构的所有一级子节点
	public List<Map<String, String>> selectByUserId(String userId, String tenantId) {
		// TODO Auto-generated method stub
		Map<String,String> param = new HashMap<String,String>();
		param.put("userId", userId);
		param.put("tenantId", tenantId);
		return daoHelper.queryForList(orgMapperNameSpace+"selectByUserId", param);
	}

	@Override
	//根据用户Id查询其所属的组织机构的所有孩子节点
	public List<Map<String, String>> selectAllByUserId(String userId, String tenantId) {
		// TODO Auto-generated method stub
		Map<String,String> param = new HashMap<String,String>();
		param.put("userId", userId);
		param.put("tenantId", tenantId);
		return daoHelper.queryForList(orgMapperNameSpace+"selectAllByUserId", param);
	}

	/**
	 * 将组织机构集合转换为树结构
	 * @param resourceList
	 * @return
	 */
	private List<Orgnization> convertTree(List<Orgnization> orgList){
		List<Orgnization> result = new ArrayList<Orgnization>();
		Map<String,Orgnization> menuMap = new HashMap<String,Orgnization>();
		if(orgList!=null&&orgList.size()>0){
			for(Orgnization api:orgList){
				menuMap.put(api.getOrgId(), api);
			}
			for(Orgnization api:orgList){
				if("root".equals(api.getOrgId())){
					result.add(api);
				}else{
					List<Orgnization> children = menuMap.get(api.getParentId()).getChildren();
					if(children != null){
						children.add(api);
						menuMap.get(api.getParentId()).setChildren(children);
					}
					else{
						children = new ArrayList<Orgnization>();
						children.add(api);
						menuMap.get(api.getParentId()).setChildren(children);
					}
				}
			}
		}
		return result;
	}
	
	@Override
	@Transactional
	//根据组织机构Id删除其所有子节点
	public void deleteByOrgId(String orgId,String path) throws Exception {
		Map<String,String> param = new HashMap<String,String>();
		param.put("orgId", orgId);
		param.put("path", path);
		int count = (int) daoHelper.queryOne(orgMapperNameSpace+"selectChildOrgTotal", param);
		if(count>0){
			throw new Exception("该节点下有子节点，请先删除子节点后再删除该节点。");
		}
		daoHelper.delete(orgMapperNameSpace+"deleteUserOrgRef", param);
		daoHelper.delete(orgMapperNameSpace+"deleteOrgByOrgId", param);
	}
	
	@Override
	//添加组织机构节点
	public int addOrg(Orgnization orgnization) {
		if(StringUtil.isEmpty(orgnization.getParentId())){
			orgnization.setPath("/"+orgnization.getOrgId());
		}else{
			orgnization.setPath(orgnization.getPath()+"/"+orgnization.getOrgId());
		}
		return daoHelper.insert(orgMapperNameSpace + "addOrg", orgnization);
	}
	
	@Override
	//修改组织机构节点
	public int updateOrg(Orgnization orgnization) {
		return daoHelper.update(orgMapperNameSpace + "updateOrg", orgnization);
	}

	@Override
	public List<Orgnization> orgListTree() {
		List<Orgnization> list = daoHelper.queryForList("com.bonc.base.organization.mapper.selectAllOrg", null);
		return convertTree(list);
	}

	
	
}
