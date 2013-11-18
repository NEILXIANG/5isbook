package com.wisbook.action.configuration;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.service.configuration.ConfigeService;
@Component("goodsType")
@Scope("prototype")
public class GoodsType extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ConfigeService configeService;

	public ConfigeService getConfigeService() {
		return configeService;
	}

	@Resource(name="goodsTypeConfigeServiceProvider")
	public void setConfigeService(ConfigeService configeService) {
		this.configeService = configeService;
	}

	public String execute() throws Exception{
		org.springframework.core.io.Resource resource = new FileSystemResource("E:\\_Mspace\\项目\\二手书交易平台\\5isbook\\src\\5isbook\\WebContent\\WEB-INF\\configuration\\goods_type.xml");
		System.out.println(resource.getURL());
		this.configeService.confige(resource.getFile());
		return Action.SUCCESS;
	}
}
