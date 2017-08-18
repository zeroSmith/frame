package com.bonc.rdpe.controller.organization;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.rdpe.util.IdUtil;
import com.bonc.rdpe.entity.orgnization.Orgnization;
import com.bonc.rdpe.service.organization.OrganizationService;

/** 
 * 组织机构相关操作controller
 * @author qxl
 * @date 2017年1月10日 下午4:12:37 
 * @version 1.0.0
 */
@Controller
@RequestMapping("/org")
public class OrganizationController {
	
	@Resource
	private OrganizationService organizationService;
	
	@RequestMapping("/index")
	public String index(){
		return "systemconfig/orgstructuremanage";
	}

	@ResponseBody
	@RequestMapping("/selectAll")
	public List<Map<String,String>> selectAll(){
		return organizationService.selectAll();
	}
	
	@ResponseBody
	@RequestMapping("/orgListTree")
	public List orgListTree(){
		return organizationService.orgListTree();
	}
	
	@ResponseBody
	@RequestMapping("/selectByOrgId")
	public List<Map<String,String>> selectByOrgId(String orgId,String tenantId){
		return organizationService.selectByOrgId(orgId,tenantId);
	}
	
	@ResponseBody
	@RequestMapping("/selectAllByOrgId")
	public List<Map<String,String>> selectAllByOrgId(String orgId,String path,String tenantId){
		return organizationService.selectAllByOrgId(orgId, path, tenantId);
	}
	
	@ResponseBody
	@RequestMapping("/selectByUserId")
	public List<Map<String,String>> selectByUserId(String userId,String tenantId){
		return organizationService.selectByUserId(userId, tenantId);
	}
	
	@ResponseBody
	@RequestMapping("/selectAllByUserId")
	public List<Map<String,String>> selectAllByUserId(String userId,String tenantId){
		return organizationService.selectAllByUserId(userId, tenantId);
	}
	
	@ResponseBody
	@RequestMapping(value="/addOrg",method=RequestMethod.POST)
	public void addOrg(Orgnization orgnization){
		orgnization.setOrgId(IdUtil.genUUID());
		organizationService.addOrg(orgnization);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateOrg",method=RequestMethod.POST)
	public void updateOrg(Orgnization orgnization){
		organizationService.updateOrg(orgnization);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteByOrgId",method=RequestMethod.POST)
	public void deleteByOrgId(HttpServletRequest request) throws Exception{
		String orgId = request.getParameter("orgId");
		String path = request.getParameter("path");
		organizationService.deleteByOrgId(orgId, path);
	}
	
}
