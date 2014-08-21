package org.etower.manager.main;

import org.etower.core.domain.EtConfig;
import org.etower.repository.main.EtConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class EtConfigMng {

	private EtConfigDao dao;
	
	@Autowired
	public void setDao(EtConfigDao dao) {
		this.dao = dao;
	}

	public EtConfig get() {
		EtConfig entity = dao.findOne((long) 1);
		return entity;
	}
}
