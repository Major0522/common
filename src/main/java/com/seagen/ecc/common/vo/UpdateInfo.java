package com.seagen.ecc.common.vo;

import java.io.Serializable;

/**
 * 
 * 程序更新信息/协议
 * 
 * 
 * @author kuangjianbo<kuangjianbo_sea@163.com>
 * @createdate 2014-05-05
 */
public class UpdateInfo implements Serializable {
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
	/**
	 * 更新文件路径
	 */
	private String filePath;
	/**
	 * 更新文件的md5值
	 */
	private String md5;

	/**
	 * 更新状态,初始0,等待更新1,更新完成2,更新失败-1
	 */
	private int state = 0;
	/**
	 * 操作信息,可能包含成功或者失败详情等信息
	 */
	private String detail;
	/**
	 * 源程序目录
	 */
	private String srcDirectory;
	/**
	 * 要备份的目录
	 */
	private String toBakDirectory;
	/**
	 * 备份生成的文件
	 */
	private String bakZip;

	/**
	 * 触发更新的命令信息
	 */
	private Object commandMessage;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Object getCommandMessage() {
		return commandMessage;
	}

	public void setCommandMessage(Object commandMessage) {
		this.commandMessage = commandMessage;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSrcDirectory() {
		return srcDirectory;
	}

	public void setSrcDirectory(String srcDirectory) {
		this.srcDirectory = srcDirectory;
	}

	public String getToBakDirectory() {
		return toBakDirectory;
	}

	public void setToBakDirectory(String toBakDirectory) {
		this.toBakDirectory = toBakDirectory;
	}

	public String getBakZip() {
		return bakZip;
	}

	public void setBakZip(String bakZip) {
		this.bakZip = bakZip;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getStartFilePath() {
		return startFilePath;
	}

	public void setStartFilePath(String startFilePath) {
		this.startFilePath = startFilePath;
	}

}
