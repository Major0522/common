package com.seagen.ecc.common.win;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seagen.ecc.utils.DateUtils;

/**
 * 
 * 执行windows脚本命令
 * 
 * @author kuangjianbo<kuangjianbo_sea@163.com>
 * @createdate 2014-05-05
 */
public class WinCmd {
	private static String tempDir = System.getProperty("java.io.tmpdir");

	public static void main(String[] args) throws IOException {
		execBat("taskkill /f /im notepad.exe");
	}

	private static Logger log = LoggerFactory.getLogger(WinCmd.class);

	/**
	 * 执行脚本文件
	 * 
	 * @param file
	 * @return
	 */
	public static boolean execBat(File file) {
		try {
			String bat = FileUtils.readFileToString(file, "GBK");
			if (file.exists() && file.getParentFile() != null) {
				bat = "cd /d " + file.getParentFile().getAbsolutePath()
						+ "\r\n" + bat;
			}
			return execBat(bat);
		} catch (IOException e) {
			log.error("exeBat failed:" + file.getAbsolutePath(), e);
			return false;
		}
	}

	/**
	 * 执行批量脚本代码
	 * 
	 * @param bat
	 * @return
	 */
	public static boolean execBat(String bat) {
		// bat += "\r\ndel %~f0\r\nexit\r\n";
		bat += "\r\nexit\r\n";
		File temp = new File(tempDir, DateUtils.getSerialNo() + ".bat");
		try {
			FileUtils.writeStringToFile(temp, bat);
			Runtime.getRuntime().exec(
					"cmd.exe /c start /MIN " + temp.getAbsolutePath());
			log.info("exeBat ok,file:" + temp.getAbsolutePath() + ",bat:\r\n"
					+ bat);
			return true;
		} catch (Exception e) {
			log.error("exeBat failed:" + bat, e);
		}
		return false;
	}

	/**
	 * 执行单条命令,并返回结果
	 * 
	 * @param cmd
	 * @return
	 */
	public List<String> execCmdAndGetOut(String cmd) {
		List<String> lines = new ArrayList<String>();
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream(), "gbk"));
			String line = "";
			while ((line = input.readLine()) != null) {
				log.debug(line);
				lines.add(line);
			}
			input.close();
		} catch (Exception e) {
			log.error("getLines error:", e);
		}
		return lines;
	}
}