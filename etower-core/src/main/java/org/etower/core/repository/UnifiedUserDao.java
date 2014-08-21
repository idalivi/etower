package org.etower.core.repository;

import java.util.List;

import org.etower.core.domain.UnifiedUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UnifiedUserDao extends PagingAndSortingRepository<UnifiedUser, Long> {

	UnifiedUser findByUserName(String userName);
	
	List<UnifiedUser> findByEmail(String email);
	
	@Modifying
	@Query("delete from UnifiedUser unif where unif.id = ?1")
	UnifiedUser deleteById(Long id);

}
