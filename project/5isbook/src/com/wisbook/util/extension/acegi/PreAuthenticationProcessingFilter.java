package com.wisbook.util.extension.acegi;

import java.io.IOException; 
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.wisbook.util.extension.acegi.providers.UserKindComparisonAware;
/**
 * 该类作用：把j_userkind set进容器汇总所有实现了UserKindComparisonAware接口的类实例
 * 具体：
 * 	取得请求中名字为：j_userkind的参数的值
 * 	取得ioc中所有实现了com.wisbook.util.extension.acegi.providers.UserKindComparisonAware接口的类
 * 	然后把j_userkind set进上面找出的那些类里面
 * @author volador
 *
 */
public class PreAuthenticationProcessingFilter implements Filter,ApplicationContextAware{
	
	public static final String ACEGI_SECURITY_FORM_USERKIND="j_userkind";
	
	private boolean initialized = false;
	private Map<?,?> targetBeans;
	private String targetClass;
	private ApplicationContext applicationContext;
	
	public String getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		if(!(request instanceof HttpServletRequest)) throw new ServletException("can only process HttpServletRequest！");
		if(!this.initialized) doInit();
		
		String userKind = obtainUserKind((HttpServletRequest)request);
		if(userKind != null && !"".equals(userKind)){
			for(Object obj:this.targetBeans.values()){
				UserKindComparisonAware comparison = (UserKindComparisonAware) obj;
				comparison.setCurrentUserKind(userKind);
			}
		}
		
		chain.doFilter(request, response);
	}
	
	/*
	 * 获得请求中的j_userkind值
	 */
	private String obtainUserKind(HttpServletRequest request) {
		return request.getParameter(ACEGI_SECURITY_FORM_USERKIND);
	}

	/*
	 * 拿到所有的容器里面实现了UserKindComparisonAware接口的类实例
	 */
	private synchronized void doInit() throws ServletException {
		if(this.targetClass == null || "".equals(this.targetClass)) throw new ServletException("targetClass must be specified!");
		Class<?> _targetClass = null;
		try{
			_targetClass = Thread.currentThread().getContextClassLoader().loadClass(targetClass);
		}catch(ClassNotFoundException ex){
			throw new ServletException("Class of type "+targetClass+" not found in classloader!");
		}
		targetBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, _targetClass,true,true);
		if(targetBeans.size() == 0) throw new ServletException("Bean context must contain at least one bean of type "+targetClass);
		for(Object key:targetBeans.keySet()){
			if(!(targetBeans.get(key) instanceof UserKindComparisonAware))
				throw new ServletException("Bean '" + key + "' does not implement com.wisbook.util.extension.acegi.providers.UserKindComparisonAware");
		}
		this.initialized = true;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {}
	
}
