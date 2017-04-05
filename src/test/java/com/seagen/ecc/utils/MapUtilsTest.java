package com.seagen.ecc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class MapUtilsTest {

	@Test
	public void test() {
		Map<String,String > params = new HashMap<String,String >();
		params.put("total_fee", "total_fee");
		params.put("trade_no", "trade_no");
		params.put("out_trade_no", "out_trade_no");
		params.put("notify_id", "notify_id");
		params.put("sign", "sign");
		
		List<String> keyList = new ArrayList<String>();
			keyList.add("total_fee");
    	keyList.add("trade_no");
    	keyList.add("out_trade_no");
    	keyList.add("notify_id");
    	keyList.add("sign");
	
		System.out.println(	MapUtils.verifyMap(params, keyList));
	}
}
