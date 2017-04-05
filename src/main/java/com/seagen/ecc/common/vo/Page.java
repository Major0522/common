package com.seagen.ecc.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息包装类
 * 
 * @author kuangjianbo<kuangjianbo_sea@163.com>
 * @createdate 2013-12-26
 * 
 */
public class Page<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int maxPageSize = 1000;
	private Integer pageSize = 15;
	private Integer currentPage = 1;
	private Integer totalPage = 1;
	private Integer totalResult=0;
	private List<T> list;
	//必须是奇数
	private Integer showNum = 11;
	private List<Integer> pageNumList = new ArrayList<Integer>();;

	public List<Integer> getPageNumList() {
		pageNumList.clear();
		int half = showNum / 2;
		int mid = half + 1;
		if (currentPage > mid) {
			mid = currentPage;
		}
		int start = mid - half;
		int end = mid + half;

		for (int i = start; i <= end; i++) {
			if (i > 0 && i <= totalPage) {
				pageNumList.add(i);
			}
		}
		return pageNumList;
	}

	public Page() {
	}

	public Page(Integer currentPage) {
		setCurrentPage(currentPage);
	}

	public Page(Integer currentPage, Integer pageSize) {
		setCurrentPage(currentPage);
		setPageSize(pageSize);
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize != null && pageSize > 0) {
			this.pageSize = pageSize;
			if (pageSize > maxPageSize) {
				this.pageSize = maxPageSize;
			}
		}
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		if (currentPage != null && currentPage > 0) {
			this.currentPage = currentPage;
		}
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(Integer totalResult) {
		this.totalResult = totalResult;
		this.totalPage = totalResult % pageSize == 0 ? totalResult / pageSize
				: totalResult / pageSize + 1;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
