package com.wisbook.service.configuration.providers;

import java.util.List; 

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.model.order.DeliveryTime;
import com.wisbook.service.configuration.AbstractConfigeService;
import com.wisbook.service.configuration.XmlFileFormatException;
import com.wisbook.util.XPath;
/**
 * 配置系统的用户角色模块
 * @author volador
 *
 */
@Component("deliveryTimeConfigeServiceProvider")
public class DeliveryTimeConfigeServiceProvider extends AbstractConfigeService {
	
	private BaseDao<DeliveryTime,Integer> deliveryTimeDao;
	
	public BaseDao<DeliveryTime, Integer> getDeliveryTimeDao() {
		return deliveryTimeDao;
	}
	
	@Resource(name="deliveryTimeDao")
	public void setDeliveryTimeDao(BaseDao<DeliveryTime, Integer> deliveryTimeDao) {
		this.deliveryTimeDao = deliveryTimeDao;
	}


	@Override
	protected void process() throws XmlFileFormatException {
		Node root = this.getRootNode();
		List<Node> timeList = XPath.selectNodes("/config/time",root);
		if(timeList != null && timeList.size() >0)
			for(Node time:timeList){
				String value = XPath.selectText("@value",time);
				if(this.assertNotNull(value)){
					DeliveryTime t = new DeliveryTime();
					t.setTime(value);
					this.deliveryTimeDao.saveEntity(t);
				}
			}
	}
	

}
