package com.seagen.ecc.common;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;

/**
 * 
 * 自定义RMIClientSocketFactory
 * 
 * @author kuangjianbo<kuangjianbo_sea@163.com>
 * @createdate 2014-02-27
 */
public class RMICustomClientSocketFactory implements RMIClientSocketFactory,
		Serializable {
	private static final long serialVersionUID = 7658110721985465345L;
	private int timeout;

	/**
	 * 设置超时时间
	 * 
	 * @param timeout
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException {
		Socket socket = new Socket();
		socket.setSoTimeout(timeout);
		socket.setSoLinger(false, 0);
		socket.setKeepAlive(false);
		socket.connect(new InetSocketAddress(host, port), timeout);
		return socket;
	}

}
