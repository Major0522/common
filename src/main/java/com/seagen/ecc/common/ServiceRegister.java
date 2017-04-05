package com.seagen.ecc.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.remoting.support.RemoteExporter;

/**
 * 动态注册服务
 * 
 * @author kuangjianbo
 * 
 */
public class ServiceRegister {

	private static Logger log = LoggerFactory.getLogger(ServiceRegister.class);
	/**
	 * 默认使用hessian <br>
	 * 协议支持:<br>
	 * org.springframework.remoting.caucho.HessianServiceExporter<br>
	 * org.springframework.remoting.caucho.BurlapServiceExporter<br>
	 * org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter<br>
	 * dubbo<br>
	 */
	private String remoteExporter = "org.springframework.remoting.caucho.HessianServiceExporter";
	/**
	 * 根据annotation来扫描bean进行发布
	 */
	private String annotation = "org.springframework.stereotype.Service";
	/**
	 * 根据packName来扫描bean进行发布
	 */
	private List<String> packNames = new ArrayList<String>();

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public List<String> getPackNames() {
		return packNames;
	}

	public void setPackNames(List<String> packNames) {
		this.packNames = packNames;
	}

	public String getRemoteExporter() {
		return remoteExporter;
	}

	public void setRemoteExporter(String remoteExporter) {
		this.remoteExporter = remoteExporter;
	}

	/**
	 * 初始化,自动扫描指定包下Repository,发布为服务
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() throws Exception {
		log.info(getClass().getName() + " init");
		AbstractRefreshableApplicationContext ac = (AbstractRefreshableApplicationContext) ApplicationHelper
				.getApplicationContext();
		ConfigurableListableBeanFactory beanFactory = ac.getBeanFactory();
		Class<? extends Annotation> annotationType = (Class<? extends Annotation>) Class
				.forName(annotation);
		Map<String, Object> services = ac
				.getBeansWithAnnotation(annotationType);
		log.info(annotationType.getSimpleName() + " count:" + services.size());
		// dubbo
		boolean isDubbo = remoteExporter.equals("dubbo");
		Class<?> serviceCla = null;
		Method intf = null;
		Method ref = null;
		Method export = null;
		Object source = null;
		if (isDubbo) {
			serviceCla = Class
					.forName("com.alibaba.dubbo.config.ServiceConfig");
			intf = serviceCla.getMethod("setInterface", Class.class);
			ref = serviceCla.getMethod("setRef", Object.class);
			source = ApplicationHelper.getFirstBean(serviceCla);
			export = serviceCla.getMethod("export");
		}
		for (Object service : services.values()) {
			Class<?> cla = null;
			if (service instanceof Proxy && AopUtils.isJdkDynamicProxy(service)) {
				Object realService = ((Advised) service).getTargetSource()
						.getTarget();
				if (isPacked(realService.getClass().getName())) {
					cla = realService.getClass();
				}
			} else if (isPacked(service.getClass().getName())) {
				cla = service.getClass();
			}
			if (cla != null && cla.getInterfaces().length > 0) {// 可发布服务的真实类
				Class<?> intfCla = cla.getInterfaces()[0];
				String beanName = null;
				if (ac.getBeansOfType(intfCla).size() == 1) {
					beanName = getServicePath(intfCla.getName());
				} else {
					log.info("too many impl class,ignore:" + intfCla.getName());
					continue;
				}
				// 暴露及注册服务
				Object exporter = null;
				if (isDubbo) {
					exporter = serviceCla.newInstance();
					BeanUtils.copyProperties(source, exporter,
							new String[] { "path" });
					intf.invoke(exporter, intfCla);
					ref.invoke(exporter, service);
					export.invoke(exporter);
				} else {
					RemoteExporter rExporter = createRemoteExporter();
					rExporter.setService(service);
					rExporter.setServiceInterface(intfCla);
					exporter = rExporter;
				}
				beanFactory.registerSingleton(beanName, exporter);// 注册服务
				beanFactory.initializeBean(exporter, beanName);
				log.info("service registered:" + beanName);
			} else {
				log.info("ignore service:" + service.getClass().getName());
			}
		}
	}

	/**
	 * 判断类是否在指定包内
	 * 
	 * @param claName
	 *            实现类名
	 * @return
	 */
	public boolean isPacked(String claName) {
		for (String s : packNames) {
			if (claName.startsWith(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取服务发布路径,如 "com.seagen.ecc.intf.dao.rt.MemberDaoService"的发布路径为
	 * "/intf/dao/rt/MemberDaoService"
	 * 
	 * @param className
	 *            接口类名
	 * @return
	 */
	public String getServicePath(String className) {
		String ret = "";
		String[] strs = className.split("\\.");
		for (int i = 3; i < strs.length; i++) {
			ret += "/" + strs[i];
		}
		return ret;
	}

	/**
	 * 根据remoteExporter名字,创建一个RemoteExporter实例
	 * 
	 * @return
	 */
	public RemoteExporter createRemoteExporter() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		return (RemoteExporter) Class.forName(remoteExporter).newInstance();
	}

	@PreDestroy
	public void destory() {
		log.info("destory:" + getClass().getName());
	}
}
