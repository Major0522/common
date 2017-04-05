package com.seagen.ecc.common;

import java.rmi.server.RemoteServer;
import java.util.Arrays;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seagen.ecc.utils.NetUtils;

public class RMISecurityInterceptor implements MethodInterceptor {
	private static Logger log = LoggerFactory.getLogger(RMISecurityInterceptor.class);
	private Set<String> allowed;

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		String clientHost = RemoteServer.getClientHost();
		boolean allowed = isAllowed(clientHost);
		log.debug(allowed + " rmi from: " + clientHost + ","
				+ methodInvocation.getMethod().getName() + ":"
				+ Arrays.toString(methodInvocation.getArguments()));

		if (allowed) {
			return methodInvocation.proceed();
		} else {
			throw new SecurityException("没有访问权限");
		}
	}

	public boolean isAllowed(String clientHost) {

		if (allowed == null) {// 不设置 ,都可以访问
			return true;
		}
		for (String s : allowed) {
			if (NetUtils.ipInRange(clientHost, s)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setAllowed(Set allowed) {
		this.allowed = allowed;
	}

}