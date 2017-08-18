/**
 * Project Name		[rdpe]
 * File Name		[com.bonc.rdpe.controller.dictionary.SystemDictController.java]
 * Creation Date	[2017年4月25日]
 * <p>
 * Copyright© 2017 www.bonc.com.cn All Rights Reserved
 * <p>
 */
package com.bonc.rdpe.controller.dictionary;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.rdpe.entity.sysdict.SystemDict;
import com.bonc.rdpe.entity.user.User;
import com.bonc.rdpe.service.dictionary.SystemDictService;
import com.bonc.rdpe.util.JsonUtils;
import com.bonc.rdpe.util.UserUtil;

/**
 * @author 王晓星
 * 
 * 2017年4月25日 下午1:53:35
 */

@Controller
@RequestMapping("/systemDict")
public class SystemDictController {
	
	@Autowired
	private SystemDictService systemDictService;
	
	@RequestMapping("/index")
	public String index(){
		return "systemconfig/system_dict";
	}
	
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<SystemDict> editSystemDict(HttpServletRequest request){
		return systemDictService.getAll();
	}
	@RequestMapping("/list")
	@ResponseBody
	public Map selectAll(HttpServletRequest request,String start,String length,String jsonStr){
		Map<String,Object> paramMap = JsonUtils.stringToCollect(jsonStr);
		User user = UserUtil.getUserResource(request);
		paramMap.put("creatorId", user.getUserId());
		Map selectAllByCondition = systemDictService.getByCondition(start, length, paramMap);
		return selectAllByCondition;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public int addSystemDict(HttpServletRequest request, SystemDict systemDict) throws Exception{
		User user = UserUtil.getUserResource(request);
		systemDict.setCreateId(user.getUserId());
		systemDict.setCreateTime(new Date());
		return systemDictService.add(systemDict);
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public int removeSystemDict(HttpServletRequest request, String propName) throws Exception{
		return systemDictService.remove(propName);
	}
	
	@RequestMapping("/getSystemDictByPropName")
	@ResponseBody
	public SystemDict getSystemDict(HttpServletRequest request ,String propName){
		return systemDictService.getByKey(propName);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public int editSystemDict(HttpServletRequest request,SystemDict systemDict) throws Exception{
		return systemDictService.edit(systemDict);
	}
	
}
