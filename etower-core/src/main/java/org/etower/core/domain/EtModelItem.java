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
@Table(name = "et_model_item")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtModelItem extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String field;
	private String itemLabel;
	private Integer priority;
	private String defValue;
	private String optValue;
	private String textSize;
	private String areaRows;
	private String areaCols;
	private String help;
	private String helpPosition;
	private int dataType;
	private boolean isSingle;
	private boolean isChannel;
	private boolean isCustom;
	private boolean isDisplay;
	
	private EtModel etModel;

	public EtModelItem() {

	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getItemLabel() {
		return itemLabel;
	}

	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	public String getOptValue() {
		return optValue;
	}

	public void setOptValue(String optValue) {
		this.optValue = optValue;
	}

	public String getTextSize() {
		return textSize;
	}

	public void setTextSize(String textSize) {
		this.textSize = textSize;
	}

	public String getAreaRows() {
		return areaRows;
	}

	public void setAreaRows(String areaRows) {
		this.areaRows = areaRows;
	}

	public String getAreaCols() {
		return areaCols;
	}

	public void setAreaCols(String areaCols) {
		this.areaCols = areaCols;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getHelpPosition() {
		return helpPosition;
	}

	public void setHelpPosition(String helpPosition) {
		this.helpPosition = helpPosition;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public boolean getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}

	public boolean getIsChannel() {
		return isChannel;
	}

	public void setIsChannel(boolean isChannel) {
		this.isChannel = isChannel;
	}

	public boolean getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(boolean isCustom) {
		this.isCustom = isCustom;
	}

	public boolean getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(boolean isDisplay) {
		this.isDisplay = isDisplay;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "MODEL_ID")
	public EtModel getEtModel() {
		return etModel;
	}

	public void setEtModel(EtModel etModel) {
		this.etModel = etModel;
	}
	
	public void init() {
		if (getPriority() == null) {
			setPriority(10);
		}
	}

}
