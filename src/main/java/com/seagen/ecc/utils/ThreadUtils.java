package com.seagen.ecc.utils;

import java.util.logging.Logger;

public class ThreadUtils {

	public static void printAllThreads() {
		Thread[] thread = findAllThreads();
		
		log.info("All threads =============================== ");
		log.info("All threads count is " + thread.length);
		
		for ( int i = 0; i < thread.length; i++ ) {
			Thread t = thread[i];
			log.info(">>>>>>thread id is " + t.getId());
			log.info("thread name is " + t.getName());
			log.info("thread is alive ? " + t.isAlive());
			log.info("thread is daemon ? " + t.isDaemon());
			log.info("thread is interrupted ? " + t.isInterrupted());
			log.info("thread group name is " + t.getThreadGroup().getName());
		}
	
	}
	
	public static Thread[] findAllThreads() {
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		ThreadGroup topGroup = group;

		// 遍历线程组树，获取根线程组
		while (group != null) {
			topGroup = group;
			group = group.getParent();
		}
		
		// 激活的线程数加倍
		int estimatedSize = topGroup.activeCount() * 2;
		
		Thread[] slackList = new Thread[estimatedSize];
		// 获取根线程组的所有线程
		int actualSize = topGroup.enumerate(slackList);
		// copy into a list that is the exact size
		Thread[] list = new Thread[actualSize];
		
		System.arraycopy(slackList, 0, list, 0, actualSize);
		
		return list;
	}
	
	private static Logger log = Logger.getLogger(ThreadUtils.class.getName());
}
