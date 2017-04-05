package com.seagen.ecc.common;

/**
 * Ascii编码的定义单元
 * 
 * @version	1.0 2010-12-2 
 * @author	吴天斌
 */
public final class AsciiSet {
	/** 空, 0x00 */
	public static final char NUL = 0;		// 0x00
	/** 文件头的开始, 0x01 */
	public static final char SOH = 1;		// 0x01
	/** 文本的开始, 0x02 */
	public static final char STX = 2;		// 0x02
	/** 文本的结束, 0x03 */
	public static final char ETX = 3;		// 0x03
	/** 传输的结束, 0x04 */
	public static final char EOT = 4;		// 0x04
	/** 询问, 0x05 */
	public static final char ENQ = 5;		// 0x05
	/** 确认, 0x06 */
	public static final char ACK = 6;		// 0x06
	/** 响铃, 0x07 */
	public static final char BEL = 7;		// 0x07
	/** 后退, 0x08 */
	public static final char BS = 8;			// 0x08
	/** 水平跳格, 0x09 */
	public static final char HT = 9;			// 0x09
	/** 换行, 0x0a */
	public static final char LF = 10;		// 0x0A
	/** 垂直跳格, 0x0b */
	public static final char VT = 11;		// 0x0B
	/** 格式馈给, 0x0c */
	public static final char FF = 12;		// 0x0C
	/** 回车, 0x0d */
	public static final char CR = 13;		// 0x0D
	/** 向外移出, 0x0e */
	public static final char SO = 14;		// 0x0E
	/** 向内移入, 0x0f */
	public static final char SI = 15;		// 0x0F
	/** 数据传送换码, 0x10 */
	public static final char DLE = 16;		// 0x10
	/** 设备控制1, 0x11 */
	public static final char DC1 = 17;		// 0x11
	/** 设备控制2, 0x12 */
	public static final char DC2 = 18;		// 0x12
	/** 设备控制3, 0x13 */
	public static final char DC3 = 19;		// 0x13
	/** 设备控制4, 0x14 */
	public static final char DC4 = 20;		// 0x14
	/** 否定, 0x15 */
	public static final char NAK = 21;		// 0x15
	/** 同步空闲, 0x16 */
	public static final char SYN = 22;		// 0x16
	/** 传输块结束, 0x17 */
	public static final char ETB = 23;		// 0x17
	/** 取消, 0x18 */
	public static final char CAN = 24;		// 0x18
	/** 媒体结束, 0x19 */
	public static final char EM = 25;		// 0x19
	/** 减, 0x1a */
	public static final char SUB = 26;		// 0x1A
	/** 退出, 0x1b */
	public static final char ESC = 27;		// 0x1B
	/** 域分隔符, 0x1c */
	public static final char FS = 28;		// 0x1C
	/** 组分隔符, 0x1d */
	public static final char GS = 29;		// 0x1D
	/** 记录分隔符, 0x1e */
	public static final char RS = 30;		// 0x1E
	/** 单元分隔符, 0x1f */
	public static final char US = 31;		// 0x1F
	/** 空格, 0x20 */
	public static final char Space = 32;		// 0x20
	/** Delete, 0x7f */
	public static final char DEL = 127;		// 0x7F
}
