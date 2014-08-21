package org.etower.common.datatype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginationType<T> {

	private String JPQL; // jpql语句
	private String workFile; // 调用的页面
	private Integer pageNo; // 当前页数
	private Integer beginNo; // 当前开始记录数
	private Integer pageSize; // 单页显示条数
	private Integer totalCount; // 总记录数
	private String CSS; // 显示样式
	public Map<String, String> mapQueryString = new HashMap<String, String>(); // 其他参数map
	@SuppressWarnings("unused")
	private String strQueryString; // 其他参数
	private List<T> pageContent;// 当前分页的内容列表
	private String pageHTML;// 当前分类代码；
	private String orderBy;// 排序依据
	private Boolean isAsc;// 是否正序
	private Integer intMaxPage;// 最大显示分页数量
	private String[] pageTitle; // 分页提示信息
	private Object[] objJPQL;// 查询参数对象

	public PaginationType() {

	}

	public PaginationType(String JPQL, Integer pageNo, Integer pageSize,
			String orderBy, Boolean isAsc, Object[] objJPQL) {
		super();
		this.JPQL = JPQL;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.orderBy = orderBy;
		this.isAsc = isAsc;
		this.objJPQL = objJPQL;
	}

	public PaginationType(String JPQL, String workFile, Integer pageNo,
			Integer pageSize, String CSS, String strQueryString,
			String orderBy, Boolean isAsc, Object[] objJPQL) {
		super();
		this.JPQL = JPQL;
		this.workFile = workFile;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.CSS = CSS;
		this.strQueryString = strQueryString;
		this.orderBy = orderBy;
		this.isAsc = isAsc;
		this.objJPQL = objJPQL;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	// 如果当前页面大小参数intMaxPage为空或小于等于0 为奇数则+1返回 否则则默认为10
	public Integer getIntMaxPage() {
		if (intMaxPage == null || intMaxPage <= 0)
			// 如果为空则返回默认10
			return 10;
		else if (intMaxPage % 2 == 1)
			// 如果为奇数则 +1 返回
			return intMaxPage + 1;
		else
			return intMaxPage;
	}

	// 如果当前页参数pageNo为空或小于等于0 则默认为1
	public Integer getPageNo() {
		if (beginNo != null && beginNo >= 0) {
			return (beginNo / pageSize + 1);
		} else {
			if (pageNo == null || pageNo <= 0)
				return 1;
			else
				return pageNo;
		}
	}

	// 如果当前页面大小参数pageSize为空或小于等于0 则默认为30
	public Integer getPageSize() {
		if (pageSize == null || pageSize <= 0)
			return 30;
		else
			return pageSize;
	}

	public Boolean getIsAsc() {
		return isAsc;
	}

	public List<T> getPageContent() {
		return pageContent;
	}

	public Object[] getObjJPQL() {
		return objJPQL;
	}

	// 如果样式表参数CSS为空或长度等于0 则默认为 CSS
	public String getCSS() {
		if (CSS == null || CSS.length() == 0)
			return "manu";
		else
			return CSS;
	}

	public String getJPQL() {
		return JPQL;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public String getStrQueryString() {
		StringBuffer strTemp = new StringBuffer("");
		if (mapQueryString.size() > 0) {
			strTemp.append("?");
			for (Map.Entry<String, String> entry : mapQueryString.entrySet()) {
				strTemp.append("&").append(entry.getKey()).append("=")
						.append(entry.getValue());
			}
			return strTemp.toString().replace("?&", "");
		} else {
			return null;
		}
	}

	public String getWorkFile() {
		if (this.workFile == null) {
			this.workFile = "";
		}
		return workFile;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public void setIntMaxPage(Integer intMaxPage) {
		this.intMaxPage = intMaxPage;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setIsAsc(Boolean isAsc) {
		this.isAsc = isAsc;
	}

	public void setPageContent(List<T> pageContent) {
		this.pageContent = pageContent;
	}

	public void setObjJPQL(Object[] objJPQL) {
		this.objJPQL = objJPQL;
	}

	public void setCSS(String CSS) {
		this.CSS = CSS;
	}

	public void setJPQL(String JPQL) {
		this.JPQL = JPQL;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public void setStrQueryString(String strQueryString) {
		this.strQueryString = strQueryString;
	}

	public void setWorkFile(String workFile) {
		this.workFile = workFile;
	}

	public Map<String, String> getMapQueryString() {
		return mapQueryString;
	}

	public void setMapQueryString(Map<String, String> mapQueryString) {
		this.mapQueryString = mapQueryString;
	}

	public void addQueryString(String strKey, Object objValue) {
		if (objValue != null && strKey != null && strKey.length() > 0
				&& objValue.toString().length() > 0) {
			this.mapQueryString.put(strKey, objValue.toString());
		}
	}

	public String[] getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String[] pageTitle) {
		this.pageTitle = pageTitle;
	}

	public Integer getBeginNo() {
		return beginNo;
	}

	public void setBeginNo(Integer beginNo) {
		this.beginNo = beginNo;
	}
}
