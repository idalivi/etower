package org.etower.core.repository;

import java.util.Date;

import org.etower.core.domain.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthenticationDao extends PagingAndSortingRepository<Authentication, Long> {

	Page<Authentication> findByUserId(Integer userId, Pageable pageRequest);
	
	@Modifying
	@Query("delete from Authentication auth where auth.updateTime <= ?1")
	int deleteExpire(Date d);
	
	@Modifying
	@Query("delete from Authentication auth where auth.authId = ?1")
	void deleteByAuthId(String id);
	
	Authentication findByAuthId(String authId);

}
