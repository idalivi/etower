package org.etower.core.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.etower.common.jpa.entity.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "et_role")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtRole extends IdEntity {
	private static final long serialVersionUID = 1L;

	private boolean isSuper;
	private int priority;
	private String remark;
	private String roleName;

	private EtSite etSite;
	private List<EtUser> etUsers;
	private Set<EtRolePermission> etRolePerm;

	public EtRole() {
	}

	public boolean getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "SITE_ID")
	public EtSite getEtSite() {
		return etSite;
	}

	public void setEtSite(EtSite etSite) {
		this.etSite = etSite;
	}

	@ManyToMany
	@JoinTable(name = "et_user_role", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public List<EtUser> getEtUsers() {
		return etUsers;
	}

	public void setEtUsers(List<EtUser> etUsers) {
		this.etUsers = etUsers;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "etRole")
	public Set<EtRolePermission> getEtRolePerm() {
		return etRolePerm;
	}

	public void setEtRolePerm(Set<EtRolePermission> etRolePerm) {
		this.etRolePerm = etRolePerm;
	}

}