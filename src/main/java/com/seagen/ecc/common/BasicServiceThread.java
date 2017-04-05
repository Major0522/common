package com.seagen.ecc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seagen.ecc.utils.ExceptionUtils;

/**
 * 服务线程基类.
 * 
 * @version	1.0 2010-11-29 
 * @author	吴天斌
 */
public abstract class BasicServiceThread extends Thread {
	
	private static Logger log = LoggerFactory.getLogger(BasicServiceThread.class);

	/** 每执行一次服务处理过程间的延时时间 */
	private int delay;
	
	/** 服务初始化处理的延时时间 */
	private int initialDelay;

	/** 服务线程是否可用 */
	private boolean enabled;
	
	/** 是否取消服务，用于停止线程处理过程 */
	private boolean canceled;

	/**
	 * 构造器.
	 * 
	 * @param name     服务名称.
	 * @param delay    延时时间.
	 * @param initialDelay  初始化延时时间.
	 * @param enabled  是否启用.
	 */
	public BasicServiceThread(String name, int delay, int initialDelay, 
			boolean enabled) 
	{
		setName(name);
		setDelay(delay);
		setInitialDelay(initialDelay);
		
		if (enabled) {
			enable();
		} else { 
			disable();
		}
		
		this.canceled = false;
	}

	/**
	 * 返回延时时间.
	 * 
	 * @return 延时时间，线程执行过程的延时时间.
	 */
	public int getDelay() {
		return this.delay;
	}

	/**
	 * 设置延时时间.
	 * 
	 * @param delay 延时时间.
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}

	/**
	 * 返回初始化延时时间.
	 * 
	 * @return 初始化延时时间.
	 */
	public int getInitialDelay() {
		return this.initialDelay;
	}

	/**
	 * 设置初始化延时时间.
	 * 
	 * @param initialDelay 初始化延时时间.
	 */
	public void setInitialDelay(int initialDelay) {
		this.initialDelay = initialDelay;
	}

	/**
	 * 返回是否有效.
	 * 
	 * @return 服务是否有效.
	 */
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * 启用（激活）服务.
	 */
	public void enable() {
		this.enabled = true;
	}

	/**
	 * 关闭（停止）服务.
	 */
	public void disable() {
		this.enabled = false;
	}

	/**
	 * 返回是否取消服务.
	 * 
	 * @return 是否取消服务.
	 */
	public boolean isCanceled() {
		return this.canceled;
	}

	/**
	 * 取消（中断）服务.
	 */
	public void cancel() {
		this.canceled = true;

		interrupt();
		
		try {
			this.join();
		} catch (InterruptedException e) {
			// Ignore this.
		}
	}

	/**
	 * 服务的执行处理过程.
	 */
	@Override
	public void run() {
		while (!isCanceled()) {
			try {
//				log.debug((isEnabled() ? "BasicServiceThread Running..." 
//						: "** disabled **"));
				
				Thread.sleep(getDelay());

				if (isEnabled()) {
					process();
				}
//				
//				sleep(getDelay());
			} catch (InterruptedException e) {
				if (isCanceled()) {
//					log.debug("BasicServiceThread Stopped.");
					break;
				}
				
//				log.debug("BasicServiceThread Interrupted!");
			} catch (Exception e) {
				log.error(e.getClass().getName() + " Error!");
				log.error(ExceptionUtils.getStackTrace(e));
			}
		}
	}

	/**
	 * 服务的处理函数，执行真正的服务处理.
	 * 
	 * @throws Exception
	 */
	public abstract void process() throws Exception;
	
}
