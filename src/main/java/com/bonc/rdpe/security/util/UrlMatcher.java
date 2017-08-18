package com.bonc.rdpe.security.util;

/**
 * 描述：这个接口是以前spring版本中的，现在的spring版本中不存在，由于项目需要且使用方便，故加入到项目当中 时间：2013-08-24
 * 作者：jxw
 */
public interface UrlMatcher {
	public abstract Object compile(String paramString);

	public abstract boolean pathMatchesUrl(Object paramObject, String paramString);

	public abstract String getUniversalMatchPattern();

	public abstract boolean requiresLowerCaseUrl();
}
