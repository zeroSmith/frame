package com.bonc.rdpe.security.interceptor;

import java.util.List;

import com.bonc.rdpe.entity.resources.Resources;
import com.bonc.rdpe.security.util.AntUrlPathMatcher;
import com.bonc.rdpe.security.util.Constant;
import com.bonc.rdpe.security.util.UrlMatcher;
import com.bonc.rdpe.security.wrap.SecurityRequestWrap;

/**
 * @author 作者: 吕一凡 
 * @date 2017年1月9日 下午9:20:11 
 * @version 版本: 1.0
 * 资源拦截器类
 */
public class ResourceSecurityInterceptor extends AbstractSecurityInterceptor{
	
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();

	@Override
	public void doValidation(SecurityRequestWrap securityRequestWrap)throws Exception {
		String targetUrl = this.getCurrentRequestUrl(securityRequestWrap);//获得当前请求的路径
		String rootUrl = this.getRootRequestUrl(securityRequestWrap);
		String tempUrl = "";
		if(!rootUrl.isEmpty()){//判断项目是否为根路径
			tempUrl = rootUrl;
		}
		@SuppressWarnings("unchecked") 
		List<Resources> menuList = (List<Resources>) 
		this.securityRepository.getResourceByType(Constant.ResourceType.MenuResourceList
				, securityRequestWrap.getReq());//获得缓存中的菜单资源
		if(menuList == null){
			this.doNext(securityRequestWrap);
			return;
		}
		for(Resources resource : menuList){//遍历菜单资源查看
			if(urlMatcher.pathMatchesUrl(tempUrl+resource.getExt2(),targetUrl)){//查看资源是否匹配菜单资源
				if(resource.getIsAuth().equals(Resources.trueAuth)){//查看用户是否有有资源权限
					this.doNext(securityRequestWrap);//如果有权限则继续下一个拦截器
					break;
				}else{//如果没有 权限需要跳转到没有权限页面
					this.getSecurityHandle().failHandle(securityRequestWrap);
					break;
				}
			}
		}
	}
}
