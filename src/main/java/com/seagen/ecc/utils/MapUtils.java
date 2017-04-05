package com.seagen.ecc.utils;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapUtils {
	private static Logger logger = LoggerFactory.getLogger(MapUtils.class.getName());
    /**
     * 
    *
    * @Title: verifyMap 
    * @Description: 判断map中的key是否包含所有list的值 
    * @param @param params
    * @param @param list
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws
     */
    public static boolean verifyMap(Map<?, ?> params,List<?> list){
    	boolean check = true;
    	for (Object object : list) {
			if(!params.containsKey(object)){
				logger.warn("miss key:" +  object.toString());
				check = false;
				break;
			}
		}
    	return check;
    }
}
