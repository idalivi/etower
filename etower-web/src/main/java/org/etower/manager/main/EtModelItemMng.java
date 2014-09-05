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
	
	public EtModelItem findById(Long id) {
		EtModelItem entity = etModelItemDao.findOne(id);
		return entity;
	}
	
	@Transactional(readOnly = false)
	public EtModelItem save(EtModelItem bean) {
		bean.init();
		etModelItemDao.save(bean);
		return bean;
	}
	
	@Transactional(readOnly = false)
	public void saveList(List<EtModelItem> list) {
		for (EtModelItem item : list) {
			save(item);
		}
	}
	
	@Transactional(readOnly = false)
	public void updatePriority(Long[] wids, Integer[] priority,
			String[] label, Boolean[] single, Boolean[] display) {
		EtModelItem item;
		for (int i = 0, len = wids.length; i < len; i++) {
			item = findById(wids[i]);
			item.setItemLabel(label[i]);
			item.setPriority(priority[i]);
			item.setIsSingle(single[i]);
			item.setIsDisplay(display[i]);
		}
	}
	
	@Transactional(readOnly = false)
	public EtModelItem update(EtModelItem bean) {
		bean.init();
		etModelItemDao.save(bean);
		return bean;
	}
	
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		etModelItemDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deleteByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			deleteById(ids[i]);
		}
	}
}
