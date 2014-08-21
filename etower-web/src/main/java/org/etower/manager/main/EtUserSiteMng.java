package org.etower.manager.main;

import org.etower.core.domain.EtSite;
import org.etower.core.domain.EtUser;
import org.etower.core.domain.EtUserSite;
import org.etower.repository.main.EtUserSiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class EtUserSiteMng {

	private EtUserSiteDao etUserSiteDao;
	
	@Autowired
	public void setEtUserSiteDao(EtUserSiteDao etUserSiteDao) {
		this.etUserSiteDao = etUserSiteDao;
	}

	public EtUserSite save(EtSite etSite, EtUser etUser,  Byte step, Boolean allChannel) {
		EtUserSite etUserSite = new EtUserSite();
		etUserSite.setEtSite(etSite);
		etUserSite.setEtUser(etUser);
		etUserSite.setCheckStep(step);
		etUserSite.setAllChannel(allChannel);
		etUserSiteDao.save(etUserSite);
		return etUserSite;
	}
	
	public void deleteById(Long id) {
		etUserSiteDao.deleteById(id);
	}
	
	public int deleteBySiteId(Long siteId) {
		return etUserSiteDao.deleteBySiteId(siteId);
	}
}
