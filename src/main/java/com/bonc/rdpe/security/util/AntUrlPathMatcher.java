package com.bonc.rdpe.security.util;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
/**
 * 描述：这个类是以前spring版本中的工具类，现在的spring版本中不存在，由于项目需要且使用方便，故加入到项目当中
 * 时间：2013-08-24
 * 作者：jxw
 * */
public class AntUrlPathMatcher implements UrlMatcher{
	private boolean requiresLowerCaseUrl;
	private PathMatcher pathMatcher;

	public AntUrlPathMatcher(){
	    this(true);
	}
	
	public AntUrlPathMatcher(boolean requiresLowerCaseUrl){
	   this.requiresLowerCaseUrl = true;
	   this.pathMatcher = new AntPathMatcher();
       this.requiresLowerCaseUrl = requiresLowerCaseUrl;
	}

	@Override
	public Object compile(String path) {
		if (this.requiresLowerCaseUrl) {
			return path.toLowerCase();
		}
		return path;
	}

	public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {
		this.requiresLowerCaseUrl = requiresLowerCaseUrl;
	}

	@Override
	public boolean pathMatchesUrl(Object path, String url) {
		if ((path.equals("/**")) || (path.equals("**"))) {
			return true;
			    }
			return this.pathMatcher.match((String)path, url);
	}

	@Override
	public String getUniversalMatchPattern() {
		return"/**";
    }

	@Override
	public boolean requiresLowerCaseUrl() {
		return this.requiresLowerCaseUrl;
	}
	
	public String toString() {
		return super.getClass().getName() + "[requiresLowerCase='" + this.requiresLowerCaseUrl + "']";
	}


	
}
