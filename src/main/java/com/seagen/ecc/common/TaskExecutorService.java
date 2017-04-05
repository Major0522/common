package com.seagen.ecc.common;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 自定义线程池的实现,对 {@link ThreadPoolExecutor} 线程策略做了部分改动,为克服
 * {@link Executors#newCachedThreadPool()}无限创建线程和
 * {@link Executors#newFixedThreadPool(int)}线程不被回收的缺陷 <br>
 * maximumPoolSize为最大线程数.当任务数大于corePoolSize但小于maximumPoolSize时候,会创建新线程来执行任务;
 * 当任务数大于maximumPoolSize时 ,任务可以被添加到队列中;前提是使用了该类的默认队列.
 * 
 * @author kuangjianbo
 * 
 */
public class TaskExecutorService implements ExecutorService {

	private ThreadPoolExecutor threadPoolExecutor;

	public TaskExecutorService(int corePoolSize, int maximumPoolSize,
			long keepAliveTime) {
		this(corePoolSize, maximumPoolSize, keepAliveTime,
				TimeUnit.MILLISECONDS, null, Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy());
	}

	public TaskExecutorService(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit) {
		this(corePoolSize, maximumPoolSize, keepAliveTime, unit, null,
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy());
	}

	public TaskExecutorService(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, ThreadFactory threadFactory) {
		this(corePoolSize, maximumPoolSize, keepAliveTime, unit, null,
				threadFactory, new ThreadPoolExecutor.AbortPolicy());

	}

	public TaskExecutorService(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, ThreadFactory threadFactory,
			RejectedExecutionHandler handler) {
		this(corePoolSize, maximumPoolSize, keepAliveTime, unit, null,
				threadFactory, handler);

	}

	@SuppressWarnings("serial")
	public TaskExecutorService(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
			RejectedExecutionHandler handler) {
		if (workQueue == null) {
			workQueue = new LinkedBlockingQueue<Runnable>() {
				public boolean offer(Runnable runnable) {
					if (threadPoolExecutor.getPoolSize() < threadPoolExecutor
							.getMaximumPoolSize()) {
						return false;
					}
					return super.offer(runnable);
				}
			};
		}
		threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
				maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory,
				handler);

	}

	@Override
	public void execute(Runnable command) {
		threadPoolExecutor.execute(command);

	}

	@Override
	public void shutdown() {
		threadPoolExecutor.shutdown();

	}

	@Override
	public List<Runnable> shutdownNow() {
		return threadPoolExecutor.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		return threadPoolExecutor.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return threadPoolExecutor.isTerminated();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		return threadPoolExecutor.awaitTermination(timeout, unit);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return threadPoolExecutor.submit(task);
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		return threadPoolExecutor.submit(task, result);
	}

	@Override
	public Future<?> submit(Runnable task) {
		return threadPoolExecutor.submit(task);
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
			throws InterruptedException {
		return threadPoolExecutor.invokeAll(tasks);
	}

	@Override
	public <T> List<Future<T>> invokeAll(
			Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException {
		return threadPoolExecutor.invokeAll(tasks, timeout, unit);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
			throws InterruptedException, ExecutionException {
		return threadPoolExecutor.invokeAny(tasks);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
			long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		return threadPoolExecutor.invokeAny(tasks, timeout, unit);
	}

}
