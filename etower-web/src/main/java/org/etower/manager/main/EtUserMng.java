package org.etower.manager.main;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.etower.common.email.EmailSender;
import org.etower.common.email.MessageTemplate;
import org.etower.core.domain.EtSite;
import org.etower.core.domain.EtUser;
import org.etower.core.domain.EtUserExt;
import org.etower.core.domain.UnifiedUser;
import org.etower.core.manager.UnifiedUserMng;
import org.etower.repository.main.EtUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class EtUserMng {
	
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private EtUserSiteMng etUserSiteMng;
	
	private EtUserDao etUserDao;

	@Autowired
	public void setEtUserDao(EtUserDao etUserDao) {
		this.etUserDao = etUserDao;
	}

	public EtUser findById(Long id) {
		return etUserDao.findOne(id);
	}

	public EtUser findByUsername(String username) {
		EtUser entity = etUserDao.findByUserName(username);
		return entity;
	}

	public EtUser registerMember(String username, String email,
			String password, String ip, Integer groupId, EtUserExt userExt) {
		return registerMember(username, email, password, ip, groupId, userExt,
				true, null, null);
	}

	public EtUser registerMember(String username, String email,
			String password, String ip, Integer groupId, EtUserExt userExt,
			Boolean activation, EmailSender sender, MessageTemplate msgTpl) {
		UnifiedUser unifiedUser = unifiedUserMng.save(username, email,
				password, ip, activation, sender, msgTpl);
		EtUser user = new EtUser();
//		user.forMember(unifiedUser);
//
//		CmsGroup group = null;
//		if (groupId != null) {
//			group = cmsGroupMng.findById(groupId);
//		} else {
//			group = cmsGroupMng.getRegDef();
//		}
//		if (group == null) {
//			throw new RuntimeException(
//					"register default member group not found!");
//		}
//		user.setGroup(group);
//		user.init();
//		dao.save(user);
//		EtUserExtMng.save(userExt, user);
		return user;
	}

	public void updateLoginInfo(Long userId, String ip) {
		Date now = new Timestamp(System.currentTimeMillis());
		EtUser user = findById(userId);
		if (user != null) {
			user.setLoginCount(user.getLoginCount() + 1);
			user.setLastLoginIp(ip);
			user.setLastLoginTime(now);
		}
	}

	public void updateUploadSize(Integer userId, Integer size) {
//		EtUser user = findById(userId);
//		user.setUploadTotal(user.getUploadTotal() + size);
//		if (user.getUploadDate() != null) {
//			if (EtUser.isToday(user.getUploadDate())) {
//				size += user.getUploadSize();
//			}
//		}
//		user.setUploadDate(new java.sql.Date(System.currentTimeMillis()));
//		user.setUploadSize(size);
	}

	public boolean isPasswordValid(Long id, String password) {
		return unifiedUserMng.isPasswordValid(id, password);
	}

	public void updatePwdEmail(Long id, String password, String email) {
		EtUser user = findById(id);
		if (!StringUtils.isBlank(email)) {
			user.setEmail(email);
		} else {
			user.setEmail(null);
		}
		unifiedUserMng.update(id, password, email);
	}

	public EtUser saveAdmin(String username, String email, String password,
			String ip, boolean viewOnly, boolean selfAdmin, int rank,
			Integer groupId, Integer[] roleIds, Integer[] channelIds,
			Integer[] siteIds, Byte[] steps, Boolean[] allChannels,
			EtUserExt userExt) {
		UnifiedUser unifiedUser = unifiedUserMng.save(username, email,
				password, ip);
		EtUser user = new EtUser();
//		user.forAdmin(unifiedUser, viewOnly, selfAdmin, rank);
//		CmsGroup group = null;
//		if (groupId != null) {
//			group = cmsGroupMng.findById(groupId);
//		} else {
//			group = cmsGroupMng.getRegDef();
//		}
//		if (group == null) {
//			throw new RuntimeException(
//					"register default member group not setted!");
//		}
//		user.setGroup(group);
//		user.init();
//		dao.save(user);
//		EtUserExtMng.save(userExt, user);
//		if (roleIds != null) {
//			for (Integer rid : roleIds) {
//				user.addToRoles(cmsRoleMng.findById(rid));
//			}
//		}
//		if (channelIds != null) {
//			Channel channel;
//			for (Integer cid : channelIds) {
//				channel = channelMng.findById(cid);
//				channel.addToUsers(user);
//			}
//		}
//		if (siteIds != null) {
//			CmsSite site;
//			for (int i = 0, len = siteIds.length; i < len; i++) {
//				site = cmsSiteMng.findById(siteIds[i]);
//				EtUserSiteMng.save(site, user, steps[i], allChannels[i]);
//			}
//		}
		return user;
	}

	public void addSiteToUser(EtUser user, EtSite site, Byte checkStep) {
		etUserSiteMng.save(site, user, checkStep, true);
	}

	public EtUser updateAdmin(EtUser bean, EtUserExt ext, String password,
			Integer groupId, Integer[] roleIds, Integer[] channelIds,
			Integer siteId, Byte step, Boolean allChannel) {
		EtUser user = updateAdmin(bean, ext, password, groupId, roleIds,
				channelIds);
		// 更新所属站点
//		EtUserSiteMng.updateByUser(user, siteId, step, allChannel);
		return user;
	}

	public EtUser updateAdmin(EtUser bean, EtUserExt ext, String password,
			Integer groupId, Integer[] roleIds, Integer[] channelIds,
			Integer[] siteIds, Byte[] steps, Boolean[] allChannels) {
		EtUser user = updateAdmin(bean, ext, password, groupId, roleIds,
				channelIds);
		// 更新所属站点
//		EtUserSiteMng.updateByUser(user, siteIds, steps, allChannels);
		return user;
	}

	private EtUser updateAdmin(EtUser bean, EtUserExt ext, String password,
			Integer groupId, Integer[] roleIds, Integer[] channelIds) {
		return null;
	}

	public EtUser updateMember(Long id, String email, String password,
			Boolean isDisabled, EtUserExt ext, Integer groupId) {
		EtUser entity = findById(id);
		if (!StringUtils.isBlank(email)) {
			entity.setEmail(email);
		}
		if (isDisabled != null) {
//			entity.setDisabled(isDisabled);
		}
		if (groupId != null) {
//			entity.setGroup(cmsGroupMng.findById(groupId));
		}
//		EtUserExtMng.update(ext, entity);
		unifiedUserMng.update(id, password, email);
		return entity;
	}
	
	public EtUser updateUserConllection(EtUser user,Integer cid,Integer operate){
		return user;
	}

	public EtUser deleteById(Long id) {
		unifiedUserMng.deleteById(id);
		EtUser bean = etUserDao.deleteById(id);
		//删除收藏信息
//		bean.clearCollection();
		return bean;
	}

	public EtUser[] deleteByIds(Long[] ids) {
		EtUser[] beans = new EtUser[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

//	public boolean usernameNotExist(String username) {
//		return dao.countByUsername(username) <= 0;
//	}
//	
//	public boolean usernameNotExistInMember(String username){
//		return dao.countMemberByUsername(username)<= 0;
//	}
//
//	public boolean emailNotExist(String email) {
//		return dao.countByEmail(email) <= 0;
//	}	
}
