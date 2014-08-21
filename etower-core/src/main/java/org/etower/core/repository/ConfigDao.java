package org.etower.core.repository;

import org.etower.core.domain.Config;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConfigDao extends PagingAndSortingRepository<Config, Integer> {

	
}
