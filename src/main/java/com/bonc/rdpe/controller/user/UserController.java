package com.bonc.rdpe.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.rdpe.util.JsonUtils;
import com.bonc.rdpe.util.UserUtil;
import com.bonc.rdpe.entity.user.User;
import com.bonc.rdpe.service.organization.OrganizationService;
import com.bonc.rdpe.service.role.RoleService;
import com.bonc.rdpe.service.user.UserService;

/**
 * 用户管理相关操作Controller
 * @author 作者: 吕一凡 
 * @date 2017年1月11日 上午11:50:37 
 * @version 版本: 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private OrganizationService organizationService;

	@ResponseBody
	@RequestMapping("/selectPage")
	public Map selectPage(String start,String length,String jsonStr){
		Map<String,Object> paramMap = JsonUtils.stringToCollect(jsonStr);
		Map<String,Object> map = userService.getUserByCondition(paramMap, start, length);
		return map;
	}
	
	@RequestMapping("/index")
	public String index(){
		return "systemconfig/usermanage";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getUserById",method=RequestMethod.POST)
	public Object selectUserById(String userId){
		User user = this.userService.selectByUserId(userId);
		user.setPassword("");
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public int  update(User u,String orgIds){
		u.setOrgId(orgIds);
		return userService.update(u);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public int  delete(String id) throws Exception{
		return userService.deleteByUserId(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public int  insert(User u,HttpServletRequest request,String orgIds) throws Exception{
		u.setCreaterId(UserUtil.getUserResource(request).getUserId());
		u.setOrgId(orgIds);
		return userService.insert(u);
	}
	
	@ResponseBody
	@RequestMapping(value="/resetPasswd",method=RequestMethod.POST)
	public String  resetPasswd(String userId)throws Exception{
	    this.userService.initPasswd(userId);
	    return "1";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/org",method=RequestMethod.POST)
	public Object  org(String userId)throws Exception{
	    return organizationService.selectAll();
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getUserRole",method=RequestMethod.POST)
	public Object  getUserRole(String userId)throws Exception{
		return userService.selectRoleByUser(userId);
	}
	
	@ResponseBody
	@RequestMapping(value="/role",method=RequestMethod.POST)
	public Object  role(String userId)throws Exception{
		return roleService.selectAll();
	}
	
	@ResponseBody
	@RequestMapping(value="/userAuth",method=RequestMethod.POST)
	public String  userAuth(String jsonStr,String userId)throws Exception{
		List list = JsonUtils.toList(jsonStr, Map.class);
		userService.userAuth(list, userId);
		return "1";
	}
}
