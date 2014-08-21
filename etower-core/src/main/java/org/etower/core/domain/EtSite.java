package org.etower.core.domain;

import static org.etower.Constants.RES_PATH;
import static org.etower.Constants.TPL_BASE;
import static org.etower.common.web.Constants.DEFAULT;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.etower.common.jpa.entity.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "et_site")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtSite extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String domain;
	private String sitePath;
	private String siteName;
	private String shortName;
	private String protocol;
	private String dynamicSuffix;
	private String staticSuffix;
	private String staticDir;
	private Boolean isIndexToRoot;
	private Boolean isStaticIndex;
	private String localeAdmin;
	private String localeFront;
	private String tplSolution;
	private Byte finalStep;
	private Byte afterCheck;
	private Boolean isRelativePath;
	private Boolean isRecycleOn;
	private String domainAlias;
	private String domainRedirect;
	
	private Long configId;
	private Long ftpId;

	private EtConfig etConfig;
	private Ftp ftp;
	private List<EtRole> etRoles;
	private List<EtLog> etLogs;

	public EtSite() {
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSitePath() {
		return sitePath;
	}

	public void setSitePath(String sitePath) {
		this.sitePath = sitePath;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getDynamicSuffix() {
		return dynamicSuffix;
	}

	public void setDynamicSuffix(String dynamicSuffix) {
		this.dynamicSuffix = dynamicSuffix;
	}

	public String getStaticSuffix() {
		return staticSuffix;
	}

	public void setStaticSuffix(String staticSuffix) {
		this.staticSuffix = staticSuffix;
	}

	public String getStaticDir() {
		return staticDir;
	}

	public void setStaticDir(String staticDir) {
		this.staticDir = staticDir;
	}

	public Boolean getIsIndexToRoot() {
		return isIndexToRoot;
	}

	public void setIsIndexToRoot(Boolean isIndexToRoot) {
		this.isIndexToRoot = isIndexToRoot;
	}

	public Boolean getIsStaticIndex() {
		return isStaticIndex;
	}

	public void setIsStaticIndex(Boolean isStaticIndex) {
		this.isStaticIndex = isStaticIndex;
	}

	public String getLocaleAdmin() {
		return localeAdmin;
	}

	public void setLocaleAdmin(String localeAdmin) {
		this.localeAdmin = localeAdmin;
	}

	public String getLocaleFront() {
		return localeFront;
	}

	public void setLocaleFront(String localeFront) {
		this.localeFront = localeFront;
	}

	public String getTplSolution() {
		return tplSolution;
	}

	public void setTplSolution(String tplSolution) {
		this.tplSolution = tplSolution;
	}

	public Byte getFinalStep() {
		return finalStep;
	}

	public void setFinalStep(Byte finalStep) {
		this.finalStep = finalStep;
	}

	public Byte getAfterCheck() {
		return afterCheck;
	}

	public void setAfterCheck(Byte afterCheck) {
		this.afterCheck = afterCheck;
	}

	public Boolean getIsRelativePath() {
		return isRelativePath;
	}

	public void setIsRelativePath(Boolean isRelativePath) {
		this.isRelativePath = isRelativePath;
	}

	public Boolean getIsRecycleOn() {
		return isRecycleOn;
	}

	public void setIsRecycleOn(Boolean isRecycleOn) {
		this.isRecycleOn = isRecycleOn;
	}

	public String getDomainAlias() {
		return domainAlias;
	}

	public void setDomainAlias(String domainAlias) {
		this.domainAlias = domainAlias;
	}

	public String getDomainRedirect() {
		return domainRedirect;
	}

	public void setDomainRedirect(String domainRedirect) {
		this.domainRedirect = domainRedirect;
	}

	@Transient
	public Long getConfigId() {
		return configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	@Transient
	public Long getFtpId() {
		return ftpId;
	}

	public void setFtpId(Long ftpId) {
		this.ftpId = ftpId;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "CONFIG_ID")
	public EtConfig getEtConfig() {
		return etConfig;
	}

	public void setEtConfig(EtConfig etConfig) {
		this.etConfig = etConfig;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "FTP_ID")
	public Ftp getFtp() {
		return ftp;
	}

	public void setFtp(Ftp ftp) {
		this.ftp = ftp;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "etSite")
	public List<EtRole> getEtRoles() {
		return etRoles;
	}

	public void setEtRoles(List<EtRole> etRoles) {
		this.etRoles = etRoles;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "etSite")
	public List<EtLog> getEtLogs() {
		return etLogs;
	}

	public void setEtLogs(List<EtLog> etLogs) {
		this.etLogs = etLogs;
	}
	
	/**
	 * 获得模板路径。如：/WEB-INF/t/etower/www
	 * 
	 * @return
	 */
	@Transient
	public String getTplPath() {
		return TPL_BASE + "/" + getSitePath();
	}
	
	/**
	 * 获得模板方案路径。如：/WEB-INF/t/etower/www/default
	 * 
	 * @return
	 */
	@Transient
	public String getSolutionPath() {
		return TPL_BASE + "/" + getSitePath() + "/" + getTplSolution();
	}
	
	/**
	 * 获得模板资源路径。如：/r/etower/www
	 * 
	 * @return
	 */
	@Transient
	public String getResPath() {
		return RES_PATH + "/" + getSitePath();
	}
	
	public void init() {
		if (StringUtils.isBlank(getProtocol())) {
			setProtocol("http://");
		}
		if (StringUtils.isBlank(getTplSolution())) {
			setTplSolution(DEFAULT);
		}
	}

}