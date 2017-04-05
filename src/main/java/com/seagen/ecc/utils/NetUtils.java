package com.seagen.ecc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import com.seagen.ecc.common.win.WinUtils;

public class NetUtils {
	public static void main(String[] args) {		
		System.out.println(getMachineCode());
		System.out.println(ipInRange("192.168.1.1", "192.168.0.0/22"));
		System.out.println(ipInRange("192.168.1.1", "192.168.0.0/23"));
		System.out.println(ipInRange("192.168.1.1", "192.168.0.0/24"));
		System.out.println(ipInRange("192.168.1.1", "192.168.0.0/32"));
		System.out.println(ipInRange("192.168.0.6", "192.168.0.0/24"));


		String[] gatewayAddresses = getGatewayAddresses();
		for (String gateway: gatewayAddresses) {
			System.out.println(gateway);
		}
	}

	public static String getLocalHostName() {
		String hostName = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			hostName = addr.getHostName();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hostName;
	}

	public static String[] getAllLocalHostIP() {
		String[] ret = null;
		try {
			String hostName = getLocalHostName();
			if (hostName.length() > 0) {
				InetAddress[] addrs = InetAddress.getAllByName(hostName);
				if (addrs.length > 0) {
					ret = new String[addrs.length];
					for (int i = 0; i < addrs.length; i++) {
						ret[i] = addrs[i].getHostAddress();
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}

	public static String[] getHardwareAddress() {
		List<String> ret = new ArrayList<String>();

		try {
			Enumeration<NetworkInterface> e = NetworkInterface
					.getNetworkInterfaces();
			while (e.hasMoreElements()) {
				NetworkInterface ni = e.nextElement();
				byte[] mac = ni.getHardwareAddress();
				if (mac == null||mac.length<6) {
					continue;
				}
				String mac_s = hexByte(mac[0]) + "-" + hexByte(mac[1]) + "-"
						+ hexByte(mac[2]) + "-" + hexByte(mac[3]) + "-"
						+ hexByte(mac[4]) + "-" + hexByte(mac[5]);
				if ("00-00-00-00-00-00".equals(mac_s)) {
					continue;
				}
				ret.add(mac_s);
			}
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		Collections.sort(ret);
		return ret.toArray(new String[ret.size()]);
	}

	public static String[] getGatewayAddresses() {
		final int ADDRESS_MIN_LENGTH = 7; 
		List<String> gatewayAddresses = new ArrayList<String>();
		
		Runtime runtime = Runtime.getRuntime();
		String command = "cmd.exe /c ipconfig | findstr /i \"默认网关\"";
		try {
			Process process = runtime.exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
			String line = null;
			while ((line = reader.readLine())!=null) {
				//System.out.println(line);
				String gateway = line.substring(line.indexOf(":") + 1).trim();
				if (gateway.length() > ADDRESS_MIN_LENGTH) {
					//System.out.println(gateway);
					gatewayAddresses.add(gateway);
				}
			}
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return gatewayAddresses.toArray(new String[gatewayAddresses.size()]);
	}
	
	private static String hexByte(byte b) {
		String s = "000000" + Integer.toHexString(b);
		return s.substring(s.length() - 2);
	}

	public static String getMachineCode() {
		try {
			if (PlatformUtil.isWindows()) {// windows下通过cpu和主板来获取
				return WinUtils.getMachineCodeMd5();
			} else {
				return MD5Utils.stringMD5(Arrays.toString(NetUtils
						.getHardwareAddress()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static boolean ipInRange(String ip, String cidr) {
		String[] ips = ip.split("\\.");
		int ipAddr = (Integer.parseInt(ips[0]) << 24)
				| (Integer.parseInt(ips[1]) << 16)
				| (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);
		int type = Integer.parseInt(cidr.replaceAll(".*/", ""));
		int mask = 0xFFFFFFFF << (32 - type);
		String cidrIp = cidr.replaceAll("/.*", "");
		String[] cidrIps = cidrIp.split("\\.");
		int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24)
				| (Integer.parseInt(cidrIps[1]) << 16)
				| (Integer.parseInt(cidrIps[2]) << 8)
				| Integer.parseInt(cidrIps[3]);

		return (ipAddr & mask) == (cidrIpAddr & mask);
	}
}
