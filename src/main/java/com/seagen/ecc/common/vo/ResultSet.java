package com.seagen.ecc.common.vo;

import java.util.List;

/**
 * 结果数据集对象。
 * 
 * @author wutianbin
 *
 */
public class ResultSet {
	private PageInfo pageInfo;

	private List<Object> resultList;

	private Object otherInfo;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<Object> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object> resultList) {
		this.resultList = resultList;
	}

	public Object getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(Object otherInfo) {
		this.otherInfo = otherInfo;
	}

}
