package com.bonc.rdpe.entity.orgnization;

import java.util.List;

/**
 * 组织架构节点实体
 * @author "YanS"
 * @date 2017年1月12日 上午11:00:00 
 * @version 1.0.0
 */
public class Orgnization {
	private String orgId;
	private String orgName;
	private String parentId;
	private Long ord;
	private String path;
	private String tenantId;
	private List<Orgnization> children;
	private String text;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Orgnization> getChildren() {
		return children;
	}
	public void setChildren(List<Orgnization> children) {
		this.children = children;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Long getOrd() {
		return ord;
	}
	public void setOrd(Long ord) {
		this.ord = ord;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	
}
