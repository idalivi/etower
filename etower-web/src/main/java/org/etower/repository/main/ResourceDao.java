package org.etower.repository.main;

import java.util.List;

import org.etower.core.domain.Resource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResourceDao extends PagingAndSortingRepository<Resource, Long> {

	@Modifying
	@Query("delete from Resource resour where resour.id = ?1")
	void deleteById(Long id);
	
	public List<Resource> findById(Long Id);
	
	@Modifying
	@Query("select resour from Resource resour where resour.parentId = ?1 order by priority")
	public List<Resource> findByParentId(Long parentId);
}
