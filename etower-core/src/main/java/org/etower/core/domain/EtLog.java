package org.etower.core.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.etower.common.jpa.entity.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "et_log")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtLog extends IdEntity {
	private static final long serialVersionUID = 1L;

	private Integer category;
	private String content;
	private String ip;
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTime;
	private String title;
	private String url;

	private EtUser etUser;
	private EtSite etSite;

	public EtLog() {
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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