package com.bonc.rdpe.controller.resources;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.rdpe.util.UserUtil;
import com.bonc.rdpe.entity.resources.Resources;
import com.bonc.rdpe.service.resources.ResourceService;

/**
 * @author 作者: 吕一凡 
 * @date 2017年1月11日 下午4:35:35 
 * @version 版本: 1.0
 */
@Controller
@RequestMapping("/resource")
public class ResourcesController {

	/*@Resource
	private SecurityRequestWrap securityRequestWrap;*/
	
	@Resource
	private ResourceService resourceService;
	
	@ResponseBody
	@RequestMapping("/selectAll")
	public List<Resources> selectAll(String userId){
		return resourceService.selectResourcesList();
	}
	
	@ResponseBody
	@RequestMapping(value="/selectResourceTree")
	public List selectResourceTree(){
		return resourceService.resourcesListTree();
	}
	
	@ResponseBody
	@RequestMapping(value="/userResourceTree")
	public List userResourceTree(String userId){
		return resourceService.userMenuTree(userId);
	}
	
	@RequestMapping("/index")
	public String index(){
		return "systemconfig/resourcemanag";
	}
	
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public int  update(Resources r){
		return resourceService.update(r);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public int  delete(String id) throws Exception{
		return resourceService.deleteByResourceId(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public int  insert(Resources r,HttpServletRequest request){
		r.setCreateId(UserUtil.getUserResource(request).getUserId());
		return resourceService.insert(r);
	}
}
