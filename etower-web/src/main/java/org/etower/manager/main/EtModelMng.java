package org.etower.manager.main;

import java.util.List;

import org.etower.core.domain.EtModel;
import org.etower.repository.main.EtModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class EtModelMng {

	private EtModelDao etModelDao;

	@Autowired
	public void setEtModelDao(EtModelDao etModelDao) {
		this.etModelDao = etModelDao;
	}

	public List<EtModel> getList() {
		return (List<EtModel>) etModelDao.findAll();
	}
	
	public EtModel findById(Long id) {
		EtModel entity = etModelDao.findOne(id);
		return entity;
	}
	
	@Transactional(readOnly = false)
	public EtModel save(EtModel bean) {
		etModelDao.save(bean);
		return bean;
	}
	
	@Transactional(readOnly = false)
	public EtModel update(EtModel bean) {
		etModelDao.save(bean);
		return bean;
	}
	
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		etModelDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deleteByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			deleteById(ids[i]);
		}
	}
}
