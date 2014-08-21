package org.etower.core.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.etower.common.jpa.entity.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "et_config")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtConfig extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String contextPath;
	@Temporal(TemporalType.DATE)
	private Date countClearTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date countCopyTime;
	private String dbFileUri;
	private String defaultImg;
	private String downloadCode;
	private Integer downloadTime;
	private String emailEncoding;
	private String emailHost;
	private String emailPassword;
	private String emailPersonal;
	private String emailUsername;
	private Boolean isUploadToDb;
	private String loginUrl;
	private Integer markAlpha;
	private String markColor;
	private String markContent;
	private Integer markHeight;
	private String markImage;
	private Integer markOffsetX;
	private Integer markOffsetY;
	private Boolean markOn;
	private Integer markPosition;
	private Integer markSize;
	private Integer markWidth;
	private Integer port;
	private String processUrl;
	private String servletPoint;

	private List<EtSite> etSites;
	private List<EtConfigAttr> etConfigAttrs;

	public EtConfig() {
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public Date getCountClearTime() {
		return countClearTime;
	}

	public void setCountClearTime(Date countClearTime) {
		this.countClearTime = countClearTime;
	}

	public Date getCountCopyTime() {
		return countCopyTime;
	}

	public void setCountCopyTime(Date countCopyTime) {
		this.countCopyTime = countCopyTime;
	}

	public String getDbFileUri() {
		return dbFileUri;
	}

	public void setDbFileUri(String dbFileUri) {
		this.dbFileUri = dbFileUri;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public String getDownloadCode() {
		return downloadCode;
	}

	public void setDownloadCode(String downloadCode) {
		this.downloadCode = downloadCode;
	}

	public Integer getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(Integer downloadTime) {
		this.downloadTime = downloadTime;
	}

	public String getEmailEncoding() {
		return emailEncoding;
	}

	public void setEmailEncoding(String emailEncoding) {
		this.emailEncoding = emailEncoding;
	}

	public String getEmailHost() {
		return emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getEmailPersonal() {
		return emailPersonal;
	}

	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}

	public String getEmailUsername() {
		return emailUsername;
	}

	public void setEmailUsername(String emailUsername) {
		this.emailUsername = emailUsername;
	}

	public Boolean getIsUploadToDb() {
		return isUploadToDb;
	}

	public void setIsUploadToDb(Boolean isUploadToDb) {
		this.isUploadToDb = isUploadToDb;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public Integer getMarkAlpha() {
		return markAlpha;
	}

	public void setMarkAlpha(Integer markAlpha) {
		this.markAlpha = markAlpha;
	}

	public String getMarkColor() {
		return markColor;
	}

	public void setMarkColor(String markColor) {
		this.markColor = markColor;
	}

	public String getMarkContent() {
		return markContent;
	}

	public void setMarkContent(String markContent) {
		this.markContent = markContent;
	}

	public Integer getMarkHeight() {
		return markHeight;
	}

	public void setMarkHeight(Integer markHeight) {
		this.markHeight = markHeight;
	}

	public String getMarkImage() {
		return markImage;
	}

	public void setMarkImage(String markImage) {
		this.markImage = markImage;
	}

	public Integer getMarkOffsetX() {
		return markOffsetX;
	}

	public void setMarkOffsetX(Integer markOffsetX) {
		this.markOffsetX = markOffsetX;
	}

	public Integer getMarkOffsetY() {
		return markOffsetY;
	}

	public void setMarkOffsetY(Integer markOffsetY) {
		this.markOffsetY = markOffsetY;
	}

	public Boolean getMarkOn() {
		return markOn;
	}

	public void setMarkOn(Boolean markOn) {
		this.markOn = markOn;
	}

	public Integer getMarkPosition() {
		return markPosition;
	}

	public void setMarkPosition(Integer markPosition) {
		this.markPosition = markPosition;
	}

	public Integer getMarkSize() {
		return markSize;
	}

	public void setMarkSize(Integer markSize) {
		this.markSize = markSize;
	}

	public Integer getMarkWidth() {
		return markWidth;
	}

	public void setMarkWidth(Integer markWidth) {
		this.markWidth = markWidth;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getProcessUrl() {
		return processUrl;
	}

	public void setProcessUrl(String processUrl) {
		this.processUrl = processUrl;
	}

	public String getServletPoint() {
		return servletPoint;
	}

	public void setServletPoint(String servletPoint) {
		this.servletPoint = servletPoint;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "etConfig")
	public List<EtSite> getEtSites() {
		return etSites;
	}

	public void setEtSites(List<EtSite> etSites) {
		this.etSites = etSites;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "etConfig")
	public List<EtConfigAttr> getEtConfigAttrs() {
		return etConfigAttrs;
	}

	public void setEtConfigAttrs(List<EtConfigAttr> etConfigAttrs) {
		this.etConfigAttrs = etConfigAttrs;
	}

}