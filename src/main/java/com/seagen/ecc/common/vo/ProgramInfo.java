package com.seagen.ecc.common.vo;

import java.io.Serializable;

/**
 * 
 * 要守护的进程信息,为守护进程提供信息和行为方式
 * 
 * 
 * @author kuangjianbo<kuangjianbo_sea@163.com>
 * @createdate 2014-05-06
 */
public class ProgramInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 启动程序脚本或者快捷方式路径
	 */
	private String startFilePath;
	/**
	 * 程序进程名字或者ID
	 */
	private String process;

	public String getStartFilePath() {
		return startFilePath;
	}

	public void setStartFilePath(String startFilePath) {
		this.startFilePath = startFilePath;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

}
