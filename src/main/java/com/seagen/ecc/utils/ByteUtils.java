package com.seagen.ecc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 字节码转换工具
 * 
 * @author kuangjianbo
 */
public class ByteUtils {
	public static void main(String[] args) {
	}

	public static String obj2hex(Object obj) {
		return byte2hex(obj2byte(obj));
	}

	public static Object hex2obj(String hex) {
		return byte2obj(hex2byte(hex));
	}

	public static String byte2hex(byte[] buf) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			int hi = buf[i] >> 4 & 0x0f;
			int lo = buf[i] & 0x0f;
			sBuffer.append((char) (hi > 9 ? (hi - 10 + 'a') : (hi + '0')));
			sBuffer.append((char) (lo > 9 ? (lo - 10 + 'a') : (lo + '0')));
		}
		return sBuffer.toString();
	}

	public static byte[] hex2byte(String hex) {
		char[] ch = hex.toCharArray();
		int len = ch.length;
		byte[] buf = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			int hi = ch[i];
			hi = (hi >= 'a' && hi <= 'f') ? (hi + 10 - 'a') : (hi - '0');
			int lo = ch[i + 1];
			lo = (lo >= 'a' && lo <= 'f') ? (lo + 10 - 'a') : (lo - '0');
			int re = (hi << 4) + lo;
			buf[i / 2] = (byte) re;
		}
		return buf;
	}

	public static byte[] obj2byte(Object obj) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static Object byte2obj(byte[] buf) {
		ByteArrayInputStream bis = new ByteArrayInputStream(buf);
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(bis);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
