package org.etower.core.manager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.etower.common.page.Pagination;
import org.etower.common.security.BadCredentialsException;
import org.etower.common.security.UsernameNotFoundException;
import org.etower.common.web.session.SessionProvider;
import org.etower.core.domain.Authentication;
import org.etower.core.domain.UnifiedUser;
import org.etower.core.repository.AuthenticationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class AuthenticationMng {

	private Logger log = LoggerFactory.getLogger(AuthenticationMng.class);
	
	/**
	 * 认证信息session key
	 */
	public static final String AUTH_KEY = "auth_key";
	
	/**
	 * 登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param ip
	 *            登录IP
	 * @return 认证信息
	 * @throws UsernameNotFoundException
	 *             用户名没有找到
	 * @throws BadCredentialsException
	 *             错误的认证信息，比如密码错误
	 */
	public Authentication login(String username, String password, String ip,
			HttpServletRequest request, HttpServletResponse response,
			SessionProvider session) throws UsernameNotFoundException,
			BadCredentialsException {
		UnifiedUser user = unifiedUserMng.login(username, password, ip);
		Authentication auth = new Authentication();
		auth.setUser(user);
		auth.setUserName(user.getUserName());
		auth.setEmail(user.getEmail());
		auth.setLoginIp(ip);
		save(auth);
		session.setAttribute(request, response, AUTH_KEY, auth.getAuthId());
		return auth;
	}
	
	/**
	 * 注册后登录
	 * 
	 * @param user
	 * @param ip
	 *            登录IP
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public Authentication activeLogin(UnifiedUser user, String ip,
			HttpServletRequest request, HttpServletResponse response,
			SessionProvider session) {
		unifiedUserMng.activeLogin(user, ip);
		Authentication auth = new Authentication();
		auth.setUser(user);
		auth.setUserName(user.getUserName());
		auth.setEmail(user.getEmail());
		auth.setLoginIp(ip);
		save(auth);
		session.setAttribute(request, response, AUTH_KEY, auth.getAuthId());
		return auth;
	}

	/**
	 * 通过认证ID，获得认证信息。本方法会检查认证是否过期。
	 * 
	 * @param authId
	 *            认证ID
	 * @return 返回Authentication对象。如果authId不存在或已经过期，则返回null。
	 */
	public Authentication retrieve(String authId) {
		long current = System.currentTimeMillis();
		// 是否刷新数据库
		if (refreshTime < current) {
			refreshTime = getNextRefreshTime(current, interval);
			int count = authenticationDao.deleteExpire(new Date(current - timeout));
			log.info("refresh Authentication, delete count: {}", count);
		}
		Authentication auth = findById(authId);
		if (auth != null && auth.getUpdateTime().getTime() + timeout > current) {
			auth.setUpdateTime(new Timestamp(current));
			return auth;
		} else {
			return null;
		}
	}

	public Long retrieveUserIdFromSession(SessionProvider session,
			HttpServletRequest request) {
		String authId = (String) session.getAttribute(request, AUTH_KEY);
		if (authId == null) {
			return null;
		}
		Authentication auth = retrieve(authId);
		if (auth == null) {
			return null;
		}
		return auth.getUser().getId();
	}

	public void storeAuthIdToSession(SessionProvider session,
			HttpServletRequest request, HttpServletResponse response,
			String authId) {
		session.setAttribute(request, response, AUTH_KEY, authId);
	}

	/**
	 * 获得认证分页信息
	 * 
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            页数
	 * @return
	 */
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
//		Pagination page = authenticationDao.getPage(pageNo, pageSize);
//		return page;
		return null;
	}

	/**
	 * 根据认证ID查找认证信息
	 * 
	 * @param id
	 *            认证ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public Authentication findById(String authId) {
		return authenticationDao.findByAuthId(authId);
	}

	/**
	 * 保存认证信息
	 * 
	 * @param bean
	 * @return
	 */
	public Authentication save(Authentication bean) {
		bean.setAuthId(StringUtils.remove(UUID.randomUUID().toString(), '-'));
		bean.init();
		authenticationDao.save(bean);
		return bean;
	}

	/**
	 * 删除认证信息
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public void deleteById(String id) {
		authenticationDao.deleteByAuthId(id);
	}

	/**
	 * 删除认证信息
	 * 
	 * @param ids
	 * @return
	 */
	public void deleteByIds(String[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			deleteById(ids[i]);
		}
	}

	// 过期时间
	private int timeout = 30 * 60 * 1000; // 30分钟

	// 间隔时间
	private int interval = 4 * 60 * 60 * 1000; // 4小时

	// 刷新时间。
	private long refreshTime = getNextRefreshTime(System.currentTimeMillis(),
			this.interval);

	@Autowired
	private UnifiedUserMng unifiedUserMng;
	private AuthenticationDao authenticationDao;

	@Autowired
	public void setAuthenticationDao(AuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}

	/**
	 * 设置认证过期时间。默认30分钟。
	 * 
	 * @param timeout
	 *            单位分钟
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout * 60 * 1000;
	}

	/**
	 * 设置刷新数据库时间。默认4小时。
	 * 
	 * @param interval
	 *            单位分钟
	 */
	public void setInterval(int interval) {
		this.interval = interval * 60 * 1000;
		this.refreshTime = getNextRefreshTime(System.currentTimeMillis(),
				this.interval);
	}

	/**
	 * 获得下一个刷新时间。
	 * 
	 * 
	 * 
	 * @param current
	 * @param interval
	 * @return 随机间隔时间
	 */
	private long getNextRefreshTime(long current, int interval) {
		return current + interval;
		// 为了防止多个应用同时刷新，间隔时间=interval+RandomUtils.nextInt(interval/4);
		// return current + interval + RandomUtils.nextInt(interval / 4);
	}

}
