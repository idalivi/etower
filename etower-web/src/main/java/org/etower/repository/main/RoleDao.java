package org.etower.repository.main;

import org.etower.core.domain.EtRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleDao extends PagingAndSortingRepository<EtRole, Long> {

	@Modifying
	@Query("delete from EtRole role where role.id = ?1")
	void deleteById(Long id);
}
