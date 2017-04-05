package com.seagen.ecc.common;

/**
 * 定义整个系统公共常量。
 * 
 * @author wutianbin
 *
 */
public interface ConstDefs {
	/** 电话号码的长度 */
	public final int TELEPHONE_CODE_LENGTH = 11;
	
	/** 接口函数执行成功时返回的结果，其值为 0 */
	public final int C_RUN_SUCCESS = 0;

	/** 接口函数执行失败时返回的结果，其值为 -1 */
	public final int C_RUN_FAULT = -1;

	/** 对象不存在，其值为 -2 */
	public final int C_OBJ_NOT_EXIST = -2;

	/** 已经存在，其值为 -4 */
	public final int C_OBJ_IS_EXIST = -4;
	
	/** 已经被其他数据引用 */
	public final int C_OBJ_BE_USED = -5;
	
	/** 重新执行 */
	public final int C_RUN_RETRY = -6;
		
}
