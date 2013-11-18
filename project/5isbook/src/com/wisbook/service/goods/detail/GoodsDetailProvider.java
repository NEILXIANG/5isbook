package com.wisbook.service.goods.detail;

import java.util.List;
import java.util.Map;

import com.wisbook.service.goods.manager.StoreInfo;

/**
 * 提供商品信息,其中可以查看处理后的结果，与处理时产生的错误
 * @author volador
 *
 */
public interface GoodsDetailProvider {
    /**
     * 拿到商品细节
     * @return
     */
	List<GoodsDetail> getGoodsDetails();
	/**
	 * 查看转换的过程是否出现了错误
	 * @return
	 */
	boolean hasError();
	/**
	 * 有些项已经是系统存在的
	 * @return
	 */
	boolean hasExistedRecord();
	/**
	 * 获取这次录入操作中系统已经存在的项<br>
	 * key:商品的id，value：该商品的相关信息，可以是库存位置等
	 * @return
	 */
	Map<String,StoreInfo> getExistedRecord();
	/**
	 * 拿到错误信息
	 * @return
	 */
	List<String> getErrorMessage();
	/**
	 * 拿到转换错误的信息
	 * @return
	 */
	List<String> getErrorContent();
}
