package org.etower.core.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.etower.common.jpa.entity.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "et_role_permission")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtRolePermission extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String roleUrl;

	private EtRole etRole;

	public EtRolePermission() {
	}

	public String getRoleUrl() {
		return roleUrl;
	}

	public void setRoleUrl(String roleUrl) {
		this.roleUrl = roleUrl;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "ROLE_ID")
	public EtRole getEtRole() {
		return etRole;
	}

	public void setEtRole(EtRole etRole) {
		this.etRole = etRole;
	}

}