package org.etower.core.repository;

import org.etower.core.domain.Ftp;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FtpDao extends PagingAndSortingRepository<Ftp, Long>, JpaSpecificationExecutor<Ftp> {

	@Modifying
	@Query("delete from Ftp ftp where ftp.id = ?1")
	void deleteById(Long id);
}
