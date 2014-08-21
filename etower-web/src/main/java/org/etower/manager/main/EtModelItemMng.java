package org.etower.manager.main;

import java.util.List;

import org.etower.core.domain.EtModelItem;
import org.etower.repository.main.EtModelItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class EtModelItemMng {

	private EtModelItemDao etModelItemDao;

	@Autowired
	public void setEtModelItemDao(EtModelItemDao etModelItemDao) {
		this.etModelItemDao = etModelItemDao;
	}
	
	public List<EtModelItem> getList(Long modelId, boolean isChannel, boolean hasDisabled) {
		return etModelItemDao.getList(modelId, isChannel, hasDisabled);
	}
}
