package com.wisbook.service.configuration.providers;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.model.order.GoodsType;
import com.wisbook.service.configuration.AbstractConfigeService;
import com.wisbook.service.configuration.XmlFileFormatException;
import com.wisbook.util.XPath;

/**
 * 商品种类的配置类
 * 
 * @author volador
 * 
 */
@Component("goodsTypeConfigeServiceProvider")
public class GoodsTypeConfigeServiceProvider extends AbstractConfigeService {

	private BaseDao<GoodsType, Integer> goodsTypeDao;

	@Resource(name = "goodsTypeDao")
	public void setGoodsTypeDao(BaseDao<GoodsType, Integer> goodsTypeDao) {
		this.goodsTypeDao = goodsTypeDao;
	}

	@Override
	protected void process() throws XmlFileFormatException {
		Node root = this.getRootNode();
		List<Node> list = XPath.selectNodes("/config/type", root);
		for (Node son : list) {
			String name = XPath.selectText("@name", son);
			String displayName = XPath.selectText("@display_name", son);
			String discount = XPath.selectText("@discount", son);
			String stoCode = XPath.selectText("@sto_code", son);
			GoodsType gt = null;
			try {
				 gt = new GoodsType();
				gt.setName(name);
				gt.setDisplayName(displayName);
				gt.setDiscount(new BigDecimal(discount));
				gt.setStorageCode(stoCode);
			} catch (Exception r) {
				throw new XmlFileFormatException("file error:"+name+";"+displayName+";"+discount);
			}
			if(gt != null) this.goodsTypeDao.saveEntity(gt);
		}
	}

}
