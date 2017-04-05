package com.seagen.ecc.common.win;

/**
 * 
 * 执行windows脚本命令
 * 
 * @author kuangjianbo<kuangjianbo_sea@163.com>
 * @createdate 2014-05-05
 */
public class TaskInfo {
	private String imagename;// 进程名字
	private String pid;// 进程ID
	private String sessionname;
	private String session;
	private String memusage;
	private String state;
	private String username;
	private String cputime;
	private String windowtitle;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskInfo other = (TaskInfo) obj;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		return true;
	}

	public TaskInfo() {
		super();
	}

	public TaskInfo(String pid) {
		super();
		this.pid = pid;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSessionname() {
		return sessionname;
	}

	public void setSessionname(String sessionname) {
		this.sessionname = sessionname;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getMemusage() {
		return memusage;
	}

	public void setMemusage(String memusage) {
		this.memusage = memusage;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCputime() {
		return cputime;
	}

	public void setCputime(String cputime) {
		this.cputime = cputime;
	}

	public String getWindowtitle() {
		return windowtitle;
	}

	public void setWindowtitle(String windowtitle) {
		this.windowtitle = windowtitle;
	}

}
