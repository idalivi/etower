package org.etower.repository.main;

import java.util.List;

import org.etower.core.domain.EtModelItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EtModelItemDao extends CrudRepository<EtModelItem, Long> {

	@Modifying
	@Query("select modelItem from EtModelItem modelItem where modelItem.etModel.id = ?1 and modelItem.isChannel = ?2 and modelItem.isDisplay = ?3 order by modelItem.priority asc, modelItem.id asc")
	public List<EtModelItem> getList(Long modelId, boolean isChannel, boolean hasDisabled);
	
	@Modifying
	@Query("delete from EtModelItem modelItem where modelItem.id = ?1")
	void deleteById(Long id);
}
