package org.etower.manager.main;

import java.util.List;

import org.etower.core.domain.EtRole;
import org.etower.repository.main.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class RoleMng {

	public List<EtRole> getList() {
		return (List<EtRole>) roleDao.findAll();
	}
	
	public EtRole findById(Long id) {
		EtRole entity = roleDao.findOne(id);
		return entity;
	}
	
	@Transactional(readOnly = false)
	public EtRole save(EtRole bean) {
		roleDao.save(bean);
		return bean;
	}
	
	@Transactional(readOnly = false)
	public EtRole update(EtRole bean) {
		roleDao.save(bean);
		return bean;
	}
	
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		roleDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deleteByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			deleteById(ids[i]);
		}
	}
	
	private RoleDao roleDao;

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
}
