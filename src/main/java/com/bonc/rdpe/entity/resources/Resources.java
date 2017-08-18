package com.bonc.rdpe.entity.resources;

import java.math.BigDecimal;
import java.util.List;

import com.bonc.rdpe.util.StringUtil;

public class Resources implements Cloneable {

	public static final String trueAuth = "1";

	public static final String falseAuth = "-1";

	private String id;

	private String icon;

	private String resourcesId;

	private String parentId;

	private String appSystemId;

	private String resourcesTypeId;

	private String resourcesName;

	private String url;

	private String ext1;

	private String ext2;

	private String ext3;

	private String ext4;

	private String memo;

	private BigDecimal ord;

	private String path;

	private String createId;

	private BigDecimal onlyRead;

	private String fromSign;

	private String modifyFields;

	private String tenantId;

	private List<Resources> children;

	private String text;

	private String isAuth;

	public Resources() {

	}

	public Resources(Resources resource) {
		this.id = resource.resourcesId;
		this.icon = StringUtil.isEmpty(resource.ext1) ? "&#xe603;" : resource.ext1;
		this.resourcesId = resource.resourcesId;
		this.parentId = resource.parentId;
		this.appSystemId = resource.appSystemId;
		this.resourcesTypeId = resource.resourcesTypeId;
		this.resourcesName = resource.resourcesName;
		this.url = resource.url;
		this.ext1 = resource.ext1;
		this.ext2 = resource.ext2;
		this.ext3 = resource.ext3;
		this.ext4 = resource.ext4;
		this.memo = resource.memo;
		this.ord = resource.ord;
		this.path = resource.path;
		this.createId = resource.createId;
		this.onlyRead = resource.onlyRead;
		this.fromSign = resource.fromSign;
		this.modifyFields = resource.modifyFields;
		this.tenantId = resource.tenantId;
		this.children = resource.children;
		this.text = resource.text;
		this.isAuth = resource.isAuth;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Resources> getChildren() {
		return children;
	}

	public void setChildren(List<Resources> children) {
		this.children = children;
	}

	public String getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(String resourcesId) {
		this.resourcesId = resourcesId == null ? null : resourcesId.trim();
		this.id = resourcesId == null ? null : resourcesId.trim();
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId == null ? null : parentId.trim();
	}

	public String getAppSystemId() {
		return appSystemId;
	}

	public void setAppSystemId(String appSystemId) {
		this.appSystemId = appSystemId == null ? null : appSystemId.trim();
	}

	public String getResourcesTypeId() {
		return resourcesTypeId;
	}

	public void setResourcesTypeId(String resourcesTypeId) {
		this.resourcesTypeId = resourcesTypeId == null ? null : resourcesTypeId.trim();
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName == null ? null : resourcesName.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1 == null ? null : ext1.trim();
		this.icon = ext1 == null ? null : ext1.trim();
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2 == null ? null : ext2.trim();
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3 == null ? null : ext3.trim();
	}

	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4 == null ? null : ext4.trim();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	public BigDecimal getOrd() {
		return ord;
	}

	public void setOrd(BigDecimal ord) {
		this.ord = ord;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path == null ? null : path.trim();
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	public BigDecimal getOnlyRead() {
		return onlyRead;
	}

	public void setOnlyRead(BigDecimal onlyRead) {
		this.onlyRead = onlyRead;
	}

	public String getFromSign() {
		return fromSign;
	}

	public void setFromSign(String fromSign) {
		this.fromSign = fromSign == null ? null : fromSign.trim();
	}

	public String getModifyFields() {
		return modifyFields;
	}

	public void setModifyFields(String modifyFields) {
		this.modifyFields = modifyFields == null ? null : modifyFields.trim();
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId == null ? null : tenantId.trim();
	}

	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public Resources clone() {
		return new Resources(this);
	}
}