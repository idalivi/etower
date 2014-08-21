package org.etower.repository.main;

import org.etower.core.domain.EtLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SysLogDao extends PagingAndSortingRepository<EtLog, Long>,
		JpaSpecificationExecutor<EtLog> {

	@Modifying
	@Query("delete from EtLog log where log.id = ?1")
	void deleteById(Long id);
}
