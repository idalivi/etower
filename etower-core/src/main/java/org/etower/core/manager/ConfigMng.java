package org.etower.core.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.etower.core.domain.Config;
import org.etower.core.domain.Config.ConfigLogin;
import org.etower.core.repository.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class ConfigMng {

	@Transactional(readOnly = true)
	public Map<String, String> getMap() {
		List<Config> list = (List<Config>) configDao.findAll();
		Map<String, String> map = new HashMap<String, String>(list.size());
		for (Config config : list) {
			map.put(config.getCgfKey(), config.getCgfValue());
		}
		return map;
	}
	
	@Transactional(readOnly = true)
	public ConfigLogin getConfigLogin() {
		return ConfigLogin.create(getMap());
	}
	
	private ConfigDao configDao;

	@Autowired
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}
}
