package org.etower.core.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.etower.common.jpa.entity.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "et_group")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtGroup extends IdEntity {
	private static final long serialVersionUID = 1L;

	private Integer allowMaxFile;
	private Integer allowPerDay;
	private String allowSuffix;
	private String groupName;
	private Boolean isRegDef;
	private Boolean needCaptcha;
	private Boolean needCheck;
	private Integer priority;

	private List<EtUser> etUsers;

	public EtGroup() {
	}

	public Integer getAllowMaxFile() {
		return allowMaxFile;
	}

	public void setAllowMaxFile(Integer allowMaxFile) {
		this.allowMaxFile = allowMaxFile;
	}

	public Integer getAllowPerDay() {
		return allowPerDay;
	}

	public void setAllowPerDay(Integer allowPerDay) {
		this.allowPerDay = allowPerDay;
	}

	public String getAllowSuffix() {
		return allowSuffix;
	}

	public void setAllowSuffix(String allowSuffix) {
		this.allowSuffix = allowSuffix;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Boolean getIsRegDef() {
		return isRegDef;
	}

	public void setIsRegDef(Boolean isRegDef) {
		this.isRegDef = isRegDef;
	}

	public Boolean getNeedCaptcha() {
		return needCaptcha;
	}

	public void setNeedCaptcha(Boolean needCaptcha) {
		this.needCaptcha = needCaptcha;
	}

	public Boolean getNeedCheck() {
		return needCheck;
	}

	public void setNeedCheck(Boolean needCheck) {
		this.needCheck = needCheck;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "etGroup")
	public List<EtUser> getEtUsers() {
		return etUsers;
	}

	public void setEtUsers(List<EtUser> etUsers) {
		this.etUsers = etUsers;
	}

}