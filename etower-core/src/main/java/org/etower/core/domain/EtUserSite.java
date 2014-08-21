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
@Table(name = "et_user_site")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtUserSite extends IdEntity {
	private static final long serialVersionUID = 1L;
	
	private Byte checkStep;
	private boolean allChannel;
	private EtUser etUser;
	private EtSite etSite;
	
	public Byte getCheckStep() {
		return checkStep;
	}
	
	public void setCheckStep(Byte checkStep) {
		this.checkStep = checkStep;
	}
	
	public boolean isAllChannel() {
		return allChannel;
	}
	
	public void setAllChannel(boolean allChannel) {
		this.allChannel = allChannel;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "USER_ID")
	public EtUser getEtUser() {
		return etUser;
	}
	
	public void setEtUser(EtUser etUser) {
		this.etUser = etUser;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "SITE_ID")
	public EtSite getEtSite() {
		return etSite;
	}
	
	public void setEtSite(EtSite etSite) {
		this.etSite = etSite;
	}
}
