package com.wisbook.hibernate3.listener;

import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.def.DefaultPostLoadEventListener;
import org.springframework.context.ApplicationContext;

import com.wisbook.hibernate3.inject.Injector;
import com.wisbook.hibernate3.inject.InjectorHolder;
import com.wisbook.util.springutil.ApplicationContextUtil;

/**
 * hibernate load事件的监听器,主要处理：帮load出来的model进行依赖注入<br>
 * 
 * <pre>
 * 其实此处有一疑问未解决：
 * 	本质：这个监听器在初始化的时候应该拿到ioc里面的InjectorHolder（注入者集合）,同时
 *           注入者跟注入者集合之间的依赖关系由ioc来维护。
 *  在注入者跟注入者集合之间的依赖关系上有两种解决方法：
 *  	1.通过注解：通过@Component跟@Resource来维护他们之间的依赖关系
 *  	2.通过xml，将他们之间的依赖关系在xml上进行表述
 *  问题：使用方法1时，总是没办法维护注入者跟注入者集合之间的依赖关系，通常是拿到注入者集合，但里面的注入者却为空
 *  	所以目前只能使用方法2；使用方法2时也存在一个问题，就是懒加载的问题：若把InjectorHolder的加载设置成
 *  	lazy-init = true，则会出错，不知道什么原因，网上查说是可能是【循环引用的问题】。
 *  记录这些问题，以便以后解决。
 * 
 * </pre>
 * 
 * @author volador
 * 
 */
public class DaoAwareListener extends DefaultPostLoadEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 注入者容器在ioc里面的名字
	 */
	private static String HOLDER_NAME = "injectorHolder";
	/*
	 * 注入者容器
	 */
	private InjectorHolder injectorHolder;

	public DaoAwareListener() {
		ApplicationContext ap = ApplicationContextUtil.getApplicatoinContext();
		injectorHolder = (InjectorHolder) ap.getBean(HOLDER_NAME);
	}
	
	@Override
	public void onPostLoad(PostLoadEvent event) {
		super.onPostLoad(event);
		// 具体的注入规则让注入者来掌握
		Object o = event.getEntity();
		for (Injector injector : this.injectorHolder.getInjectors()) {
			injector.inject(o);
		}
	}
}
