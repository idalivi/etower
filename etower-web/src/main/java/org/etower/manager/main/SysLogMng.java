package org.etower.manager.main;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.etower.common.persistence.DynamicSpecifications;
import org.etower.common.persistence.SearchFilter;
import org.etower.common.persistence.SearchFilter.Operator;
import org.etower.common.web.RequestUtils;
import org.etower.common.web.springmvc.MessageResolver;
import org.etower.core.domain.EtLog;
import org.etower.core.domain.EtSite;
import org.etower.core.domain.EtUser;
import org.etower.repository.main.SysLogDao;
import org.etower.web.SysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UrlPathHelper;

@Component
@Transactional(readOnly = true)
public class SysLogMng {

	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_FAILURE = 2;
	public static final int OPERATING = 3;

	public Page<EtLog> getPage(Integer category, Map<String, Object> searchParams, int pageNo, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNo, pageSize, sortType);
		Specification<EtLog> spec = buildSpecification(category, searchParams);

		return sysLogDao.findAll(spec, pageRequest);
	}

	public EtLog loginFailure(HttpServletRequest request, String title,
			String content) {
		String ip = RequestUtils.getIpAddr(request);
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		Date date = new Date();
		EtLog log = save(LOGIN_FAILURE, null, null, uri, ip, date,
				MessageResolver.getMessage(request, title), content);
		return log;
	}

	public EtLog loginSuccess(HttpServletRequest request, EtUser etUser,
			String title) {
		String ip = RequestUtils.getIpAddr(request);
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		Date date = new Date();
		EtLog log = save(LOGIN_SUCCESS, null, etUser, uri, ip, date,
				MessageResolver.getMessage(request, title), null);
		return log;
	}

	public EtLog operating(HttpServletRequest request, String title,
			String content) {
		EtSite site = SysUtils.getSite(request);
		EtUser user = SysUtils.getUser(request);
		String ip = RequestUtils.getIpAddr(request);
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		Date date = new Date();
		EtLog log = save(OPERATING, site, user, uri, ip, date,
				MessageResolver.getMessage(request, title), content);
		return log;
	}
	
	public EtLog findById(Long id) {
		EtLog entity = sysLogDao.findOne(id);
		return entity;
	}

	@Transactional(readOnly = false)
	public EtLog save(Integer category, EtSite etSite, EtUser etUser,
			String url, String ip, Date date, String title, String content) {
		EtLog log = new EtLog();
		log.setEtSite(etSite);
		log.setEtUser(etUser);
		log.setCategory(category);
		log.setIp(ip);
		log.setLogTime(date);
		log.setUrl(url);
		log.setTitle(title);
		log.setContent(content);
		save(log);
		return log;
	}

	@Transactional(readOnly = false)
	public EtLog save(EtLog bean) {
		sysLogDao.save(bean);
		return bean;
	}
	
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		sysLogDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deleteByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			deleteById(ids[i]);
		}
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNo, int pagzSize,
			String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNo - 1, pagzSize, sort);
	}
	
	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<EtLog> buildSpecification(Integer category, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("category", new SearchFilter("category", Operator.EQ, category));
		Specification<EtLog> spec = DynamicSpecifications.bySearchFilter(filters.values(), EtLog.class);
		return spec;
	}

	private SysLogDao sysLogDao;

	@Autowired
	public void setSysLogDao(SysLogDao sysLogDao) {
		this.sysLogDao = sysLogDao;
	}
}
