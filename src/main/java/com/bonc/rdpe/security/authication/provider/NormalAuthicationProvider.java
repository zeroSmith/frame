package com.bonc.rdpe.security.authication.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.bonc.rdpe.entity.log.UserLoginLog;
import com.bonc.rdpe.entity.resources.Resources;
import com.bonc.rdpe.entity.sysdict.SystemDict;
import com.bonc.rdpe.entity.user.User;
import com.bonc.rdpe.exception.LoginException;
import com.bonc.rdpe.exception.LoginException.ExceptionType;
import com.bonc.rdpe.security.authication.IAuthication;
import com.bonc.rdpe.security.repository.ISecurityRepository;
import com.bonc.rdpe.security.repository.SecurityRepositoryFactory;
import com.bonc.rdpe.security.util.Constant;
import com.bonc.rdpe.security.util.Constant.ResourceType;
import com.bonc.rdpe.service.dictionary.SystemDictService;
import com.bonc.rdpe.service.organization.OrganizationService;
import com.bonc.rdpe.service.resources.ResourceService;
import com.bonc.rdpe.service.user.UserService;
import com.bonc.rdpe.util.MD5Util;
import com.bonc.rdpe.util.OnlineUserList;
import com.bonc.rdpe.util.StringUtil;
import com.bonc.rdpe.util.UserUtil;

/**
 * @author 作者: 吕一凡 
 * @date 2017年1月9日 下午9:02:57 
 * @version 版本: 1.0
 * 普通登录用户信息校验实现类
 */
@Component("normalAuthicationProvider")
public class NormalAuthicationProvider implements IAuthication,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -768122261761681105L;

	@Resource
	private UserService userService;
	
	@Resource
	private ResourceService resourceService;
	
	@Resource
	private SystemDictService systemDictService;
	
	@Resource
	private OrganizationService organizationService;
	
	private ISecurityRepository securityRepository = SecurityRepositoryFactory.getRepository();
	
	//用户未被锁定
	public final static String UNLOCK = "1";
	
	//用户已锁定
	public final static String LOCK = "-1";
	
	private OnlineUserList ul=OnlineUserList.getInstance();
	
	//校验用户名密码是否正确，返回userId 不是loginId
	@Override
	public String authCheck(HttpServletRequest request) throws LoginException {
		String loginId = request.getParameter("loginId");
		if("".equals(loginId)){
			throw new LoginException(ExceptionType.isEmpty);
		}
		User user = userService.selectByLoginId(loginId);
		if(user == null){
			throw new LoginException(ExceptionType.notExist);
		}else if(!getMD5Passwd(request.getParameter("password")).equals(user.getPassword())){
			throw new LoginException(ExceptionType.pwdFalse);
		}else if(LOCK.equals(user.getState())){
			throw new LoginException(ExceptionType.isLocked);
		}
		return user.getUserId();
	}
	
	@Override
	public void putUserResource(HttpServletRequest request,String userId) {
		//当前登录的用户
		User user = userService.selectByUserId(userId);
		//获取的用户组织
		List<Map<String,String>> userOrganizations = organizationService.selectByUserId(userId,null);
		if(userOrganizations != null && userOrganizations.size() > 0){
			//依照当前只有一个组织，后期再改
			Map<String, String> map = userOrganizations.get(0);
			user.setOrgId(map.get("orgId"));
		}
		securityRepository.putResourceByType(Constant.ResourceType.UserResource, request, user);
	}

	@Override
	public void putUserMenuResource(HttpServletRequest request,String userId) {
		securityRepository.putResourceByType(Constant.ResourceType.UserMenuResourceList, request, resourceService.userMenuTree(userId));
	}

	@Override
	public void putMenuResource(HttpServletRequest request,String userId) {
		List<Resources> resourceList = resourceService.selectAll(userId);
		List<Resources> newResourceList = new ArrayList<Resources>();
		for(Resources r : resourceList){
			if(!StringUtil.isEmpty(r.getExt2())){
				newResourceList.add(r);
			}
		}
		securityRepository.putResourceByType(Constant.ResourceType.MenuResourceList, request, newResourceList);
	}


	@Override
	public void putButtonResource(HttpServletRequest request, String userId) {
		securityRepository.putResourceByType(Constant.ResourceType.ButtonResourceList, request, resourceService.selectAllButton(userId));
	}
	
	@Override
	public void updateUserPasswd(HttpServletRequest request, String newPasswd) {
		User user = (User) securityRepository.getResourceByType(ResourceType.UserResource, request);
		user.setPassword(newPasswd);
		user.setPwdUpdateDate(new Date());
		userService.update(user);
		securityRepository.putResourceByType(ResourceType.UserResource, request, user);
	}
	
	@Override
	public void doUserLoginLog(HttpServletRequest request, String userId) {
		UserLoginLog log = new UserLoginLog();
		log.setUserId(userId);
		log.setLoginIp(request.getRemoteAddr());
		userService.doUserLoginLog(log);
	}

	@Override
	public void invalidateSessionResource(HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
	private String getMD5Passwd(String orgPasswd){
		return MD5Util.Bit32(orgPasswd);
	}
	
	@Override
	public void countOnlineUser(HttpServletRequest request,String userId){
		ul.setUser(UserUtil.getUserResource(request));
		request.getSession().setAttribute("OnlineUserList", ul);
	}

	@Override
	public void putSysDict(HttpServletRequest request, String userId) {
		List<SystemDict> list = systemDictService.getAll();
		if(list!=null&&list.size()!=0){
			Map<String,String> map = new HashMap<>();
			for(SystemDict dict:list){
				map.put(dict.getPropName(), dict.getPropValue());
			}
			request.getSession().setAttribute("SysDictMap", map);
		}
	}

}
