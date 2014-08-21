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
@Table(name="et_config_attr")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtConfigAttr extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String attrName;
	private String attrValue;
	private EtConfig etConfig;

    public EtConfigAttr() {
    }

	public String getAttrName() {
		return this.attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return this.attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="CONFIG_ID")
	public EtConfig getEtConfig() {
		return this.etConfig;
	}

	public void setEtConfig(EtConfig etConfig) {
		this.etConfig = etConfig;
	}
	
}