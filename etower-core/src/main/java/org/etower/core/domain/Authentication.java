package org.etower.core.domain;

import java.sql.Timestamp;
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
@Table(name = "eo_authentication")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Authentication extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String authId;
	private String userName;
	private String email;
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTime;
	private String loginIp;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	private UnifiedUser user;

	public Authentication() {
	}

	//
	public void init() {
		Date now = new Timestamp(System.currentTimeMillis());
		setLoginTime(now);
		setUpdateTime(now);
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
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

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "USER_ID")
	public UnifiedUser getUser() {
		return user;
	}

	public void setUser(UnifiedUser user) {
		this.user = user;
	}

}