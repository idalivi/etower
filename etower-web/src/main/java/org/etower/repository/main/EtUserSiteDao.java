package org.etower.repository.main;

import org.etower.core.domain.EtUserSite;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EtUserSiteDao extends CrudRepository<EtUserSite, Long> {

	@SuppressWarnings("unchecked")
	public EtUserSite save(EtUserSite etUserSite);
	
	@Modifying
	@Query("delete from EtUserSite userSite where userSite.id = ?1")
	void deleteById(Long id);
	
	@Modifying
	@Query("delete from EtUserSite userSite where userSite.etSite.id = ?1")
	int deleteBySiteId(Long siteId);
}
