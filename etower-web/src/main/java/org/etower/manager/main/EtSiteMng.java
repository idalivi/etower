package org.etower.manager.main;

import java.io.IOException;
import java.util.List;

import org.etower.core.domain.EtSite;
import org.etower.core.domain.EtUser;
import org.etower.core.manager.FtpMng;
import org.etower.repository.main.EtSiteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class EtSiteMng {

	private static final Logger log = LoggerFactory.getLogger(EtSiteMng.class);

	@Autowired
	private FtpMng ftpMng;
	@Autowired
	private EtUserMng etUserMng;
	@Autowired
	private ResourceMng resourceMng;
	@Autowired
	private EtUserSiteMng etUserSiteMng;
	
	private EtSiteDao etSiteDao;

	@Autowired
	public void setEtSiteDao(EtSiteDao etSiteDao) {
		this.etSiteDao = etSiteDao;
	}
	
	public List<EtSite> getList() {
		return (List<EtSite>) etSiteDao.findAll();
	}

	public List<EtSite> getListFromCache() {
		return (List<EtSite>) etSiteDao.findAll();
	}

	public EtSite findByDomain(String domain) {
		return etSiteDao.findByDomain(domain);
	}

	public EtSite findById(Long id) {
		EtSite entity = etSiteDao.findOne(id);
		return entity;
	}

	@Transactional(readOnly = false)
	public EtSite save(EtSite currSite, EtUser currUser, EtSite bean,
			Long ftpId) throws IOException {
		if (ftpId != null) {
			bean.setFtp(ftpMng.findById(ftpId));
		}
		bean.setConfigId(currSite.getEtConfig().getId());
		bean.init();
		etSiteDao.save(bean);
		// 复制本站模板
		resourceMng.copyTplAndRes(currSite, bean);
		// 处理管理员
		etUserMng.addSiteToUser(currUser, bean, bean.getFinalStep());
		return bean;
	}

	@Transactional(readOnly = false)
	public EtSite update(EtSite bean, Long ftpId) {
		EtSite entity = findById(bean.getId());
		if (ftpId != null) {
			entity.setFtp(ftpMng.findById(ftpId));
		} else {
			entity.setFtp(null);
		}
		entity = etSiteDao.save(bean);
		return entity;
	}

	@Transactional(readOnly = false)
	public void updateTplSolution(Long siteId, String solution) {
		EtSite site = findById(siteId);
		site.setTplSolution(solution);
	}

	@Transactional(readOnly = false)
	public EtSite deleteById(Long id) {
		// 删除用户、站点关联
		etUserSiteMng.deleteBySiteId(id);
		EtSite bean = etSiteDao.deleteById(id);
		// 删除模板
		try {
			resourceMng.delTplAndRes(bean);
		} catch (IOException e) {
			log.error("delete Template and Resource fail!", e);
		}
		return bean;
	}

	@Transactional(readOnly = false)
	public EtSite[] deleteByIds(Long[] ids) {
		EtSite[] beans = new EtSite[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
}
