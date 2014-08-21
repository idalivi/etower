package org.etower.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.etower.common.web.session.SessionProvider;
import org.etower.core.domain.EtSite;
import org.etower.core.domain.EtUser;
import org.etower.core.manager.AuthenticationMng;
import org.etower.manager.main.EtSiteMng;
import org.etower.manager.main.EtUserMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 系统上下文信息拦截器
 * 
 * 包括登录信息、权限信息、站点信息
 * 
 * @author dawei.li
 *
 */
public class FrontContextInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws ServletException {
		EtSite site = null;
		List<EtSite> list = etSiteMng.getListFromCache();
		int size = list.size();
		if (size == 0) {
			throw new RuntimeException("no site record in database!");
		} else if (size == 1) {
			site = list.get(0);
		} else {
			String server = request.getServerName();
			String alias, redirect;
			for (EtSite s : list) {
				// 检查域名
				if (s.getDomain().equals(server)) {
					site = s;
					break;
				}
				// 检查域名别名
				alias = s.getDomainAlias();
				if (!StringUtils.isBlank(alias)) {
					for (String a : StringUtils.split(alias, ',')) {
						if (a.equals(server)) {
							site = s;
							break;
						}
					}
				}
				// 检查重定向
				redirect = s.getDomainRedirect();
				if (!StringUtils.isBlank(redirect)) {
					for (String r : StringUtils.split(redirect, ',')) {
						if (r.equals(server)) {
							try {
								response.sendRedirect(s.getSitePath());
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
							return false;
						}
					}
				}
			}
			if (site == null) {
				throw new SiteNotFoundException(server);
			}
		}
		
		SysUtils.setSite(request, site);

		EtUser user = null;
		Long userId = authMng.retrieveUserIdFromSession(session, request);
		if (userId != null) {
			user = etUserMng.findById(userId);
		}
		
		if (user != null) {
			SysUtils.setUser(request, user);
		}
		return true;
	}

	private SessionProvider session;
	private EtSiteMng etSiteMng;
	private EtUserMng etUserMng;
	private AuthenticationMng authMng;

	@Autowired
	public void setSession(SessionProvider session) {
		this.session = session;
	}

	@Autowired
	public void setCmsSiteMng(EtSiteMng etSiteMng) {
		this.etSiteMng = etSiteMng;
	}

	@Autowired
	public void setCmsUserMng(EtUserMng etUserMng) {
		this.etUserMng = etUserMng;
	}

	@Autowired
	public void setAuthMng(AuthenticationMng authMng) {
		this.authMng = authMng;
	}
}
