package org.etower.web;

import javax.servlet.http.HttpServletRequest;

import org.etower.core.domain.EtSite;
import org.etower.core.domain.EtUser;

/**
 * 提供系统中使用的一些公共方法
 * 
 * 比如：获得用户信息
 * 
 * @author dawei.li
 * 
 */
public class SysUtils {

	// 站点KEY
	public static final String SITE_KEY = "_site_key";
	// 用户KEY
	public static final String USER_KEY = "_user_key";

	/**
	 * 获得用户
	 * 
	 * @param request
	 * @return
	 */
	public static EtUser getUser(HttpServletRequest request) {
		return (EtUser) request.getAttribute(USER_KEY);
	}

	/**
	 * 获得用户ID
	 * 
	 * @param request
	 * @return
	 */
	public static Long getUserId(HttpServletRequest request) {
		EtUser user = getUser(request);
		if (user != null) {
			return user.getId();
		} else {
			return null;
		}
	}

	/**
	 * 设置用户
	 * 
	 * @param request
	 * @param user
	 */
	public static void setUser(HttpServletRequest request, EtUser user) {
		request.setAttribute(USER_KEY, user);
	}

	/**
	 * 获得站点
	 * 
	 * @param request
	 * @return
	 */
	public static EtSite getSite(HttpServletRequest request) {
		return (EtSite) request.getAttribute(SITE_KEY);
	}
	
	/**
	 * 设置站点
	 * 
	 * @param request
	 * @param site
	 */
	public static void setSite(HttpServletRequest request, EtSite site) {
		request.setAttribute(SITE_KEY, site);
	}
	
	/**
	 * 获得站点ID
	 * 
	 * @param request
	 * @return
	 */
	public static Long getSiteId(HttpServletRequest request) {
		return getSite(request).getId();
	}
}
