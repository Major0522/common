package com.seagen.ecc.common.vo;

/**
 * 分页信息.
 * 
 * @version	1.0 2010-10-12 
 * @author	吴天斌
 */
public class PageInfo {
	
	/** 当前页 */
	private Integer currentPage;
	
	/** 每页记录数 */
	private Integer numberPerPage;
	
	/** 合计记录数 */
	private Integer totalRecords;
	
	/** 合计页数 */
	private Integer totalPages;
	
	/** 当前页记录数 */
	private Integer currentPageRecords;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		if (currentPage == 0) {
			currentPage = 1;
		}
		
		this.currentPage = currentPage;
	}

	public Integer getNumberPerPage() {
		return numberPerPage;
	}

	public void setNumberPerPage(Integer numberPerPage) {
		this.numberPerPage = numberPerPage;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getCurrentPageRecords() {
		if (currentPageRecords == null) {
			currentPageRecords = numberPerPage;
		}
		
		return currentPageRecords;
	}

	public void setCurrentPageRecords(Integer currentPageRecords) {
		this.currentPageRecords = currentPageRecords;
	}

}
