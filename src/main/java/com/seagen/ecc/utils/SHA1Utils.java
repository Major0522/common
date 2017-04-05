package com.seagen.ecc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 计算字符串和文件的SHA1值
 * 
 * @author kuangjianbo
 */
public class SHA1Utils {
	/**
	 * @see #sha1Encoding(String, Charset)
	 * @param str
	 * @return
	 */
	public static String sha1GBKEncoding(String str) {
		Charset charset = Charset.forName("gbk");
		return sha1Encoding(str, charset);
	}

	/**
	 * @see #sha1Encoding(String, Charset)
	 * @param str
	 * @return
	 */
	public static String sha1UTFEncoding(String str) {
		Charset charset = Charset.forName("utf-8");
		return sha1Encoding(str, charset);
	}

	/**
	 * 计算字符串的SHA1值
	 * 
	 * @param str
	 *            要计算的字符串
	 * @param charset
	 *            编码
	 * @return String 得到的40位SHA1值(小写)
	 */
	public static String sha1Encoding(String str, Charset charset) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA1");
			digest.update(str.getBytes(charset));
			return ByteUtils.byte2hex(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算二进制文件的SHA1值
	 * 
	 * @param file
	 *            要计算的文件
	 * @return String 得到的40为SHA1值(小写)
	 */
	public static String sha1Encoding(File file) {
		if (file == null || !file.isFile()) {
			throw new NullPointerException("没有这个文件" + file);
		}
		FileInputStream fin = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA1");
			fin = new FileInputStream(file);
			byte buffer[] = new byte[1024];
			int len;
			while ((len = fin.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			return ByteUtils.byte2hex(digest.digest());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (fin != null) {
					fin.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
