package org.etower.service.main;

import java.util.List;

import org.etower.core.domain.EtUser;
import org.etower.repository.main.EtUserDao;
import org.springframework.beans.factory.annotation.Autowired;

public class EtUserService {
	
	@Autowired
	private EtUserDao etUserDao;

	public EtUserDao getEtUserDao() {
		return etUserDao;
	}

	public void setEtUserDao(EtUserDao etUserDao) {
		this.etUserDao = etUserDao;
	}

	@SuppressWarnings("unchecked")
	public List<EtUser> findByTheUserId(String id) {
		return (List<EtUser>) etUserDao.findByUserName(id);
	}

}
