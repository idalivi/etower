package org.etower.repository.main;

import org.etower.core.domain.EtConfig;
import org.springframework.data.repository.CrudRepository;

/**
 * 配置DAO接口
 * 
 * @author dawei.li
 *
 */
public interface EtConfigDao extends CrudRepository<EtConfig, Long> {
	
	
}
