package org.etower.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.etower.common.jpa.entity.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "et_resource")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Resource extends IdEntity {

	private static final long serialVersionUID = 1L;

	private String resouName;
	private Long parentId;
	private String modleName;
	private String actionName;
	private String paramData;
	private String remark;
	private Integer priority;
	private Boolean isDisplay;

	public Resource() {

	}

	public String getResouName() {
		return resouName;
	}

	public void setResouName(String resouName) {
		this.resouName = resouName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getModleName() {
		return modleName;
	}

	public void setModleName(String modleName) {
		this.modleName = modleName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getParamData() {
		return paramData;
	}

	public void setParamData(String paramData) {
		this.paramData = paramData;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Boolean isDisplay) {
		this.isDisplay = isDisplay;
	}

	@Transient
	public List<Resource> getListForSelect(Set<Resource> rights) {
		return getListForSelect(rights, null);
	}

	@Transient
	public List<Resource> getListForSelect(Set<Resource> rights,
			Resource exclude) {
		List<Resource> list = new ArrayList<Resource>();
		addChildToList(list, this, rights, exclude);
		return list;
	}
	
	/**
	 * 获得列表用于下拉选择。条件：有内容的栏目。
	 * @param topList 获得列表用于下拉选择。条件：有内容的栏目。
	 * @param rights
	 * @return
	 */
	public static List<Resource> getListForSelect(List<Resource> topList, Set<Resource> rights) {
		return getListForSelect(topList, rights, null);
	}
	
	public static List<Resource> getListForSelect(List<Resource> topList,
			Set<Resource> rights, Resource exclude) {
		List<Resource> list = new ArrayList<Resource>();
		for (Resource c : topList) {
			addChildToList(list, c, rights, exclude);
		}
		return list;
	}

	@Transient
	private static void addChildToList(List<Resource> list, Resource resource,
			Set<Resource> rights, Resource exclude) {
		if ((rights != null && !rights.contains(resource))
				|| (exclude != null && exclude.equals(resource))) {
			return;
		}
		list.add(resource);
//		Set<Resource> child = resource.get
//		for (Resource c : child) {
//			addChildToList(list, c, rights, exclude);
//		}
	}

}
