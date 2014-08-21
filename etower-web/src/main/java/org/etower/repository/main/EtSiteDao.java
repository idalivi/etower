package org.etower.repository.main;

import org.etower.core.domain.EtSite;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 站点DAO接口
 * 
 * @author dawei.li
 *
 */
public interface EtSiteDao extends PagingAndSortingRepository<EtSite, Long>, JpaSpecificationExecutor<EtSite> {

	public EtSite findByDomain(String domain);

	public EtSite findOne(Long siteId);

	@Modifying
	@Query("delete from EtSite site where site.id = ?1")
	public EtSite deleteById(Long id);
	
}
