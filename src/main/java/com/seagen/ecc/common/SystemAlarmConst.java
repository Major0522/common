package com.seagen.ecc.common;

/**
 * 系统告警常量定义单元。
 * 
 * @author wutianbin
 *
 */
public class SystemAlarmConst {
	/** 服务器性能告警 */
	public static final int C_SYSTEM_ALARM_SERVER = 1;
	
	/** 链路告警 */
	public static final int C_SYSTEM_ALARM_NETROUTE = 2;
	
	/** 网络设备告警 */
	public static final int C_SYSTEM_ALARM_NETDEV = 3; 
	
	/** 系统中断 */
	public static final int C_SYSTEM_ALARM_SYSBREAK = 4;
	
	/** 系统进程中断 */
	public static final int C_SYSTEM_ALARM_PROCESSBREAK = 5; 
	
	/** 硬件故障 */
	public static final int C_SYSTEM_ALARM_HARDWAREFAULT = 6; 
	
	/** 南向通信告警 */
	public static final int C_SYSTEM_ALARM_SOUTHCOMM = 7;     
	
}
