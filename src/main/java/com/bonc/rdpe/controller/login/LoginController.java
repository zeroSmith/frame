package com.bonc.rdpe.controller.login;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.rdpe.security.authication.IAuthication;
import com.bonc.rdpe.util.MD5Util;
import com.bonc.rdpe.util.ResponseMessage;
import com.bonc.rdpe.util.UserUtil;
import com.bonc.rdpe.entity.user.User;
import com.bonc.rdpe.exception.LoginException;

/**
 * 
 * @author bianshen
 *
 */
@Controller      
@RequestMapping("/login")
public class LoginController {
	
	@Resource
	private IAuthication normalAuthicationProvider;
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String Login(){
		return "login/login";
	}
	
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/actionLogin",method=RequestMethod.POST)
	public String actionLogin(HttpServletRequest request, HttpServletResponse response){
		try {
			String userId = normalAuthicationProvider.authCheck(request);
			normalAuthicationProvider.putUserResource(request, userId);
			normalAuthicationProvider.putUserMenuResource(request, userId);
			normalAuthicationProvider.putMenuResource(request,userId);
			normalAuthicationProvider.putButtonResource(request, userId);
			normalAuthicationProvider.doUserLoginLog(request, userId);
			normalAuthicationProvider.countOnlineUser(request, userId);
			normalAuthicationProvider.putSysDict(request, userId);
			response.sendRedirect(request.getContextPath()+"/platform/index");
		} catch (LoginException e) {
			request.setAttribute("message", e.getMessage().toString());
			return "login/login";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 注销登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request){
		normalAuthicationProvider.invalidateSessionResource(request);
		return "login/login";
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param oldPasswd
	 * @param newPasswd
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping("/editPwd")
	@ResponseBody
	public Object editPwd(HttpServletRequest request, HttpServletResponse response,String oldPasswd,String newPasswd) throws NoSuchAlgorithmException{
		String oldPasswdMd5 = MD5Util.Bit32(oldPasswd);
		User user = UserUtil.getUserResource(request);
		if(!user.getPassword().equals(oldPasswdMd5)){
			return ResponseMessage.createFailMessage("旧密码输入错误！");
		}
		String newPasswdMd5 = MD5Util.Bit32(newPasswd);
		if(user.getPassword().equals(newPasswdMd5)){
			return ResponseMessage.createFailMessage("新密码不能与旧密码一样！");
		}
		normalAuthicationProvider.updateUserPasswd(request, newPasswdMd5);
		return ResponseMessage.createSuccessMessage("密码修改成功！");
	}
	
	/**
	 * 获取当前登录用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getLoginUser")
	@ResponseBody
	public Object getLoginUserInfo(HttpServletRequest request){
		User user = UserUtil.getUserResource(request);
		return user.clone();
	}
}
