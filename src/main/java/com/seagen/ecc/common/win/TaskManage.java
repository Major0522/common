package com.seagen.ecc.common.win;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取windows系统任务信息
 */
public class TaskManage {

	private static Logger log = LoggerFactory.getLogger(TaskManage.class);
	public static final String kill_explorer_cmd = "taskkill /f /im explorer.exe";
	public static final String taskList_cmd = "tasklist.exe /v /fo csv /nh";

	public static void printTaskList() {
		getLines(taskList_cmd, true);
	}

	public static Map<Integer, TaskInfo> getTaskList() {
		Map<Integer, TaskInfo> map = new HashMap<Integer, TaskInfo>();
		List<String> lines = getLines(taskList_cmd, false);
		for (String line : lines) {
			String[] values = line.split("\",\"");
			if (values.length == 9) {
				TaskInfo info = new TaskInfo();
				info.setImagename(values[0].substring(1));
				info.setPid(values[1]);
				info.setSessionname(values[2]);
				info.setSession(values[3]);
				info.setMemusage(values[4]);
				info.setState(values[5]);
				info.setUsername(values[6]);
				info.setCputime(values[7]);
				info.setWindowtitle(values[8].substring(0,
						values[8].length() - 1));
				try {
					map.put(Integer.parseInt(info.getPid()), info);
				} catch (NumberFormatException e) {
					log.error("error pid:" + info.getPid());
				}
			}
		}
		return map;
	}

	public static Integer getCurrentPid() {
		final RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
		final String info = runtime.getName();
		final int index = info.indexOf("@");
		if (index != -1) {
			try {
				return Integer.parseInt(info.substring(0, index));
			} catch (NumberFormatException e) {
				log.error("can not get pid:" + info);
			}
		}
		return -1;
	}

	public static List<Integer> getPidByName(String name) {
		List<Integer> ret = new ArrayList<Integer>();
		Map<Integer, TaskInfo> map = getTaskList();
		Collection<TaskInfo> collection = map.values();
		for (TaskInfo t : collection) {
			if (name.equals(t.getImagename())) {
				ret.add(Integer.parseInt(t.getPid()));
			}
		}
		return ret;
	}

	public static boolean killByPid(int pid) {
		log.info("killByPid:" + pid);
		String cmd = "taskkill /f /pid " + pid;
		List<String> lines = getLines(cmd, false);
		if (lines.size() > 0 && lines.get(0).startsWith("成功")) {
			return true;
		}
		log.warn("killByPid failed:" + cmd);
		return false;
	}

	public static List<String> getLines(String cmd, boolean print) {
		List<String> lines = new ArrayList<String>();
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream(), "gbk"));
			String line = "";
			while ((line = input.readLine()) != null) {
				if (print) {
					log.info(line);
				}
				lines.add(line);
			}
			input.close();
		} catch (Exception e) {
			log.error("getLines error:", e);
		}
		return lines;
	}

	public static boolean isRunning(String process) {
		Map<Integer, TaskInfo> map = getTaskList();
		Integer curPid = getCurrentPid();
		if (process.matches("\\d+")) {
			Integer pid = Integer.parseInt(process);
			return map.get(pid) != null;
		}
		map.remove(curPid);
		Collection<TaskInfo> collection = map.values();
		for (TaskInfo t : collection) {
			if (process.equals(t.getImagename())) {
				return true;
			}
		}
		return false;
	}

	public static boolean stopProcess(String process) {
		log.info("stop program...");
		if (process.matches("\\d+")) {
			return killByPid(Integer.parseInt(process));
		}
		List<Integer> ids = getPidByName(process);
		Integer curId = getCurrentPid();
		ids.remove(curId);
		for (Integer id : ids) {
			killByPid(id);
		}
		return true;
	}

	public static boolean start(String startFilePath) {
		log.info("start program...startFilePath:" + startFilePath);
		String bat = "";
		File file = new File(startFilePath);
		if (file.exists() && file.getParentFile() != null) {
			bat += "cd /d " + file.getParentFile().getAbsolutePath() + "\r\n";
		}
		bat += "start \"\" \"" + startFilePath + "\"";
		return WinCmd.execBat(bat);
	}

	public static void killExplorer() {
		WinCmd.execBat(kill_explorer_cmd);
	}

	public static void main(String[] args) throws IOException {
		start("C:/ecc/ModuleControl/ServerSocket.exe");
	}
}
