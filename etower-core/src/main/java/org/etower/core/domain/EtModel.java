package org.etower.core.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.etower.common.jpa.entity.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "et_model")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtModel extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String modelName;
	private String modelPath;
	private String tplChannelPrefix;
	private String tplContentPrefix;
	private int titleImgWidth;
	private int titleImgHeight;
	private int contentImgWidth;
	private int contentImgHeight;
	private int priority;
	private boolean hasContent;
	private boolean isDisabled;
	private boolean isDef;
	
	private Set<EtModelItem> etModelItems;

	public EtModel() {

	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelPath() {
		return modelPath;
	}

	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}

	public String getTplChannelPrefix() {
		return tplChannelPrefix;
	}

	public void setTplChannelPrefix(String tplChannelPrefix) {
		this.tplChannelPrefix = tplChannelPrefix;
	}

	public String getTplContentPrefix() {
		return tplContentPrefix;
	}

	public void setTplContentPrefix(String tplContentPrefix) {
		this.tplContentPrefix = tplContentPrefix;
	}

	public int getTitleImgWidth() {
		return titleImgWidth;
	}

	public void setTitleImgWidth(int titleImgWidth) {
		this.titleImgWidth = titleImgWidth;
	}

	public int getTitleImgHeight() {
		return titleImgHeight;
	}

	public void setTitleImgHeight(int titleImgHeight) {
		this.titleImgHeight = titleImgHeight;
	}

	public int getContentImgWidth() {
		return contentImgWidth;
	}

	public void setContentImgWidth(int contentImgWidth) {
		this.contentImgWidth = contentImgWidth;
	}

	public int getContentImgHeight() {
		return contentImgHeight;
	}

	public void setContentImgHeight(int contentImgHeight) {
		this.contentImgHeight = contentImgHeight;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isHasContent() {
		return hasContent;
	}

	public void setHasContent(boolean hasContent) {
		this.hasContent = hasContent;
	}

	public boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public boolean getIsDef() {
		return isDef;
	}

	public void setIsDef(boolean isDef) {
		this.isDef = isDef;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "etModel")
	public Set<EtModelItem> getEtModelItems() {
		return etModelItems;
	}

	public void setEtModelItems(Set<EtModelItem> etModelItems) {
		this.etModelItems = etModelItems;
	}

}
