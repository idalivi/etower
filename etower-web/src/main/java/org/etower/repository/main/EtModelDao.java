package org.etower.repository.main;

import org.etower.core.domain.EtModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EtModelDao extends PagingAndSortingRepository<EtModel, Long> {
	
	@Modifying
	@Query("delete from EtModel model where model.id = ?1")
	void deleteById(Long id);
}
