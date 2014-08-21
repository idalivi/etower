package org.etower.web;

import org.etower.core.domain.EtSite;
import org.etower.core.domain.EtUser;

/**
 * 系统线程变量
 * 
 * @author dawei.li
 *
 */
public class SysThreadVariable {

	/**
	 * 当前用户线程变量
	 */
	private static ThreadLocal<EtUser> sysUserVariable = new ThreadLocal<EtUser>();
	/**
	 * 当前站点线程变量
	 */
	private static ThreadLocal<EtSite> sysSiteVariable = new ThreadLocal<EtSite>();

	/**
	 * 获得当前用户
	 * 
	 * @return
	 */
	public static EtUser getUser() {
		return sysUserVariable.get();
	}

	/**
	 * 设置当前用户
	 * 
	 * @param user
	 */
	public static void setUser(EtUser user) {
		sysUserVariable.set(user);
	}

	/**
	 * 移除当前用户
	 */
	public static void removeUser() {
		sysUserVariable.remove();
	}

	/**
	 * 获得当前站点
	 * 
	 * @return
	 */
	public static EtSite getSite() {
		return sysSiteVariable.get();
	}

	/**
	 * 设置当前站点
	 * 
	 * @param site
	 */
	public static void setSite(EtSite site) {
		sysSiteVariable.set(site);
	}

	/**
	 * 移除当前站点
	 */
	public static void removeSite() {
		sysSiteVariable.remove();
	}
	
}
