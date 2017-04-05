package com.seagen.ecc.utils;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RmiUtils {

	public static boolean bind(int port, String url, Remote rmi)
			throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(port);

		try {
			reg.bind(url, rmi);
			return true;
		} catch (AlreadyBoundException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}

		return false;
	}
	
	public static Remote lookup(String host, int port, String url) 
			throws RemoteException {
		System.out.println(host);
		System.out.println(port);
		System.out.println(url);

		Registry reg = LocateRegistry.getRegistry(host, port);
		
		try {
			for (String s : reg.list()) { 
				System.out.println(s);
			}
			
			return reg.lookup(url);
		} catch (NotBoundException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param url  "rmi://192.168.1.123:9664/BusinessForComm"
	 * @return
	 * @throws RemoteException
	 */
	public static Remote lookup(String url) 
			throws RemoteException {
		return lookup(getHostByUrl(url), getPortByUrl(url), getRmiByUrl(url));
	}

	private static String getHostByUrl(String url) {
		String tmp = url.substring(6);
		int pos = tmp.indexOf(":");
		return tmp.substring(0, pos);
	}

	private static int getPortByUrl(String url) {
		String tmp = url.substring(6);
		int pos = tmp.indexOf(":");
		int pos1 = tmp.indexOf("/");
		return Integer.parseInt(tmp.substring(pos + 1, pos1));
	}
	
	private static String getRmiByUrl(String url) {
		String tmp = url.substring(6);
		int pos1 = tmp.indexOf("/");
		return tmp.substring(pos1 + 1);
	}
	
	private final static Logger log = LoggerFactory.getLogger(RmiUtils.class);
}
