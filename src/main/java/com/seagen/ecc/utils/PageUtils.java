package com.seagen.ecc.utils;

import com.seagen.ecc.common.vo.Page;

public class PageUtils {

	@SuppressWarnings("rawtypes")
	public static void fillPageTotalResult(Page page, Integer totalResult, Integer currentPageNo) {
		if (page == null) {
			return;
		}
		
		if (currentPageNo != null && currentPageNo != 1 && totalResult != null) {
			// 如果是第一页，则在tradingVoDaoService.queryAll()中会查询记录数，所以不需要处理。
			// 不是第一页的数据时，需要用totalResult来填充page的记录数。
			int total = Math.max(totalResult.intValue(), 
					page.getTotalResult() != null? page.getTotalResult().intValue() : 0);
			page.setTotalResult(total);
		}
	}

}
