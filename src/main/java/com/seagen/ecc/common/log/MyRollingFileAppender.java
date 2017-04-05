package com.seagen.ecc.common.log;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.RollingFileAppender;

import com.seagen.ecc.utils.DateUtils;

public class MyRollingFileAppender extends RollingFileAppender {
	public static void main(String[] args) {
		String s = "log_130221125211";
		System.out.println(s.substring(s.length() - 12, s.length() - 6));
	}

	@Override
	public synchronized void setFile(String fileName, boolean append,
			boolean bufferedIO, int bufferSize) throws IOException {
		File temp = new File(fileName);
		File dir = temp.getParentFile();
		String name = temp.getName();
		String format = "yyMMdd_";
		String dstr = DateUtils.getCurrentDate(format);
		if (!name.matches("\\d{6}\\S+$")) {// 第一次启动产生新文件
			name = dstr + name;
		} else {
			String oldName = name.substring(0, 7);
			if (!dstr.equals(oldName)) {// 新的一天
				name = dstr + name.substring(7);
			}
		}
		fileName = new File(dir, name).getAbsolutePath();
		super.setFile(fileName, append, bufferedIO, bufferSize);
	}

	protected static String[] getFileNameAndExtension(String fileName) {
		int loc = fileName.indexOf('.');
		if (loc == -1) {
			return new String[] { fileName, "" };
		} else {
			return new String[] { fileName.substring(0, loc),
					fileName.substring(loc) };
		}
	}
}
