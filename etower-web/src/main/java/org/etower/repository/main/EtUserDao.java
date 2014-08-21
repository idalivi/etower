package org.etower.repository.main;

import org.etower.core.domain.EtUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户DAO接口
 * 
 * @author dawei.li
 *
 */
public interface EtUserDao extends CrudRepository<EtUser, Long> {

//	public Pagination getPage(String username, String email, Integer siteId,
//			Integer groupId, Boolean disabled, Boolean admin, Integer rank,
//			int pageNo, int pageSize);
//	
//	@SuppressWarnings("rawtypes")
//	public List getList(String username, String email, Integer siteId,
//			Integer groupId, Boolean disabled, Boolean admin, Integer rank);
//
//	public List<EtUser> getAdminList(Integer siteId, Boolean allChannel,
//			Boolean disabled, Integer rank);

//	public EtUser findOne(Long id);

	public EtUser findByUserName(String userName);

//	public int countByUsername(String username);
//	
//	public int countMemberByUsername(String username);
//
//	public int countByEmail(String email);

	@SuppressWarnings("unchecked")
	public EtUser save(EtUser bean);

	@Modifying
	@Query("delete from EtUser user where user.id = ?1")
	public EtUser deleteById(Long id);
}
