package com.seagen.ecc.utils;

/**
 * 
* @ClassName: StringReplaceUtil 
* @Description: 替换String帮助类
* @date 2015年5月8日 下午5:25:12 
* @version 1.0
 */
public class StringReplaceUtil {
	public final static String[] ADD = new String[]{"+","%2B"};
	public final static String[] SPACE = new String[]{" ","%20"};
	public final static String[] PERCENT = new String[]{"%","%25"};
	public final static String[] SPLIT = new String[]{"/","%2F"};
	public final static String[] QUESTION = new String[]{"?","%3F"};
	public final static String[] POUND_SIGN = new String[]{"#","%23"};
	public final static String[] AND = new String[]{"&","%26"};
	public final static String[] EQUALS = new String[]{"=","%3D"};
	public static String replaceAllSign(String init){
		if(init == null){
			return null;
		}
		init = init.replace(PERCENT[0],PERCENT[1]);
		init = init.replace(ADD[0],ADD[1]);
		init = init.replace(SPACE[0],SPACE[1]);
		init = init.replace(SPLIT[0],SPLIT[1]);
		init = init.replace(QUESTION[0],QUESTION[1]);
		init = init.replace(POUND_SIGN[0],POUND_SIGN[1]);
		init = init.replace(AND[0],AND[1]);
		init = init.replace(EQUALS[0],EQUALS[1]);
		return init;
	}
	public static String returnAllSign(String init){
		if(init == null){
			return null;
		}
		init = init.replace(PERCENT[1],PERCENT[0]);
		init = init.replace(ADD[1],ADD[0]);
		init = init.replace(SPACE[1],SPACE[0]);
		init = init.replace(SPLIT[1],SPLIT[0]);
		init = init.replace(QUESTION[1],QUESTION[0]);
		init = init.replace(POUND_SIGN[1],POUND_SIGN[0]);
		init = init.replace(AND[1],AND[0]);
		init = init.replace(EQUALS[1],EQUALS[0]);
		return init;
	}
	
}
