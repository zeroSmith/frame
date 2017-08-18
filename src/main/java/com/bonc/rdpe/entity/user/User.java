package com.bonc.rdpe.entity.user;

import java.util.Date;
import java.util.List;

import com.bonc.rdpe.entity.orgnization.Orgnization;

public class User implements Cloneable{
    private String userId;

    private String loginId;

    private String password;

    private String userName;

    private String sex;

    private String emall;

    private String mobile;

    private String telephone;

    private String state;

    private String pwdState;

    private String memo;

    private Date regDate;

    private Date updateDate;

    private String createrId;

    private String orgId;
    
    private String dataAuth;
    
    private Date lockDate;
    
    private long lockLoginTimes;
    
    private long pwdValidState;
    
    private long tenantAdmin;
    
    private Date pwdUpdateDate;
    
    private String tenantId;
    
    private Date  lastLoginDate;
    
    private List<Orgnization> orgnization;
    
    public User(){
    	
    }

	public User(User user) {
		super();
		this.userId = user.userId;
		this.loginId = user.loginId;
		this.userName = user.userName;
		this.sex = user.sex;
		this.emall = user.emall;
		this.mobile = user.mobile;
		this.telephone = user.telephone;
		this.state = user.state;
		this.pwdState = user.pwdState;
		this.memo = user.memo;
		this.regDate = user.regDate;
		this.updateDate = user.updateDate;
		this.createrId = user.createrId;
		this.orgId = user.orgId;
		this.dataAuth = user.dataAuth;
		this.lockDate = user.lockDate;
		this.lockLoginTimes = user.lockLoginTimes;
		this.pwdValidState = user.pwdValidState;
		this.tenantAdmin = user.tenantAdmin;
		this.pwdUpdateDate = user.pwdUpdateDate;
		this.lastLoginDate = user.lastLoginDate;
		this.tenantId = user.tenantId;
		this.orgnization = user.orgnization;
	}
	
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmall() {
		return emall;
	}

	public void setEmall(String emall) {
		this.emall = emall;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPwdState() {
		return pwdState;
	}

	public void setPwdState(String pwdState) {
		this.pwdState = pwdState;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDataAuth() {
		return dataAuth;
	}

	public void setDataAuth(String dataAuth) {
		this.dataAuth = dataAuth;
	}

	public Date getLockDate() {
		return lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public long getLockLoginTimes() {
		return lockLoginTimes;
	}

	public void setLockLoginTimes(long lockLoginTimes) {
		this.lockLoginTimes = lockLoginTimes;
	}

	public long getPwdValidState() {
		return pwdValidState;
	}

	public void setPwdValidState(long pwdValidState) {
		this.pwdValidState = pwdValidState;
	}

	public long getTenantAdmin() {
		return tenantAdmin;
	}

	public void setTenantAdmin(long tenantAdmin) {
		this.tenantAdmin = tenantAdmin;
	}

	public Date getPwdUpdateDate() {
		return pwdUpdateDate;
	}

	public void setPwdUpdateDate(Date pwdUpdateDate) {
		this.pwdUpdateDate = pwdUpdateDate;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	public List<Orgnization> getOrgnization() {
		return orgnization;
	}

	public void setOrgnization(List<Orgnization> orgnization) {
		this.orgnization = orgnization;
	}

	@Override
	public User clone() {
		return new User(this);
	}
}