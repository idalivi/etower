package org.etower.core.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.etower.common.jpa.entity.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "et_user")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtUser extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String email;
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerTime;
	private String registerIp;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;
	private String lastLoginIp;
	private Integer loginCount;
	private Integer adminRank;
	private Long uploadTotal;
	private Integer uploadSize;
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadDate;
	private boolean isAdmin;
	private boolean isViewonlyAdmin;
	private boolean isSelfAdmin;
	private boolean isDisabled;

	private List<EtRole> etRoles;
	private List<EtLog> etLogs;
	private EtGroup etGroup;

	public EtUser() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Integer getAdminRank() {
		return adminRank;
	}

	public void setAdminRank(Integer adminRank) {
		this.adminRank = adminRank;
	}

	public Long getUploadTotal() {
		return uploadTotal;
	}

	public void setUploadTotal(Long uploadTotal) {
		this.uploadTotal = uploadTotal;
	}

	public Integer getUploadSize() {
		return uploadSize;
	}

	public void setUploadSize(Integer uploadSize) {
		this.uploadSize = uploadSize;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean getIsViewonlyAdmin() {
		return isViewonlyAdmin;
	}

	public void setIsViewonlyAdmin(boolean isViewonlyAdmin) {
		this.isViewonlyAdmin = isViewonlyAdmin;
	}

	public boolean getIsSelfAdmin() {
		return isSelfAdmin;
	}

	public void setIsSelfAdmin(boolean isSelfAdmin) {
		this.isSelfAdmin = isSelfAdmin;
	}

	public boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	@ManyToMany(mappedBy = "etUsers")
	public List<EtRole> getEtRoles() {
		return etRoles;
	}

	public void setEtRoles(List<EtRole> etRoles) {
		this.etRoles = etRoles;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "GROUP_ID")
	public EtGroup getEtGroup() {
		return etGroup;
	}

	public void setEtGroup(EtGroup etGroup) {
		this.etGroup = etGroup;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "etUser")
	public List<EtLog> getEtLogs() {
		return etLogs;
	}

	public void setEtLogs(List<EtLog> etLogs) {
		this.etLogs = etLogs;
	}

//	// 判断是否是超级管理员
//	public boolean isSuper() {
//		List<EtRole> roles = getEtRoles();
//		if (roles == null) {
//			return false;
//		}
//		for (EtRole role : getEtRoles()) {
//			if (role.isSuper()) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public List<String> getPerms() {
//		List<EtRole> roles = getEtRoles();
//		if (roles == null) {
//			return null;
//		}
//		List<String> allPerms = new ArrayList<String>();
//		for (EtRole role : getEtRoles()) {
////			 allPerms.addAll(role.getPerms());
//		}
//		return allPerms;
//	}

}