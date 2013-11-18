package com.wisbook.service.goods.manager;

import com.wisbook.model.cms.Category;
import com.wisbook.model.cms.Entity;
import com.wisbook.model.order.EntityType;

/**
 * 记录存库的情况，比如存储书籍后的库存码
 * 
 * @author volador
 * 
 */
public class StoreInfo {

	// 录入正版书籍时库存码情况
	private String authorisedStoreCode;
	// 录入盗版书籍时情况
	private String prirateStoreCode;
	// 录入复印版书籍时情况
	private String copyStoreCode;
	// 导入前是否已经存在数据库中了
	private boolean isExistedBefore = false;

	/**
	 * 构造器 用于保存存库信息
	 * 
	 * @param entity
	 *            存库的实体
	 * @param numOfAuthorisedEdition
	 *            正品数量
	 * @param numOfPirateEdition
	 *            盗版数量
	 * @param numOfCopyEdition
	 *            复印版数量
	 * @param isExistedInDB
	 *            该商品存库之前是否已经在数据库中存在了
	 */
	public StoreInfo(Entity entity, int numOfAuthorisedEdition,
			int numOfPirateEdition, int numOfCopyEdition) {
		authorisedStoreCode = processStoCode(entity, numOfAuthorisedEdition,
				entity.getEntityType("authorised_edition"));
		prirateStoreCode = processStoCode(entity, numOfPirateEdition,
				entity.getEntityType("pirate_edition"));
		copyStoreCode = processStoCode(entity, numOfCopyEdition,
				entity.getEntityType("copy_edition"));
	}

	/**
	 * 获取库存码范围
	 * 
	 * @param entity
	 *            商品
	 * @param addNum
	 *            添加数量
	 * @param et
	 *            商品种类
	 * @return 此次添加后所生成的库存码范围
	 */
	private String processStoCode(Entity entity, int addNum, EntityType et) {
		String result;
		StringBuilder sb = new StringBuilder();
		String basicCode = entity.getCategory().getBasicCode();
		sb.append(basicCode);
		sb.append(Category.CATEGORY_SEPERATOR);
		sb.append(entity.getId());
		sb.append(Category.CATEGORY_SEPERATOR);
		sb.append(et.getEntityTypePK().getGoodsType().getStorageCode());
		if (addNum <= 0) {
			result = "没有添加";
		} else if (addNum == 1) {
			sb.append(et.getEntityTypePK().getGoodsType().getStorageCode());
			sb.append(et.getStorageCount());
			result = sb.toString();
		} else {
			StringBuilder fullResult = new StringBuilder(sb);
			fullResult.append(et.getStorageCount() - addNum + 1);
			fullResult.append("~");
			fullResult.append(sb);
			fullResult.append(et.getStorageCount());
			result = fullResult.toString();
		}
		return result;
	}

	public String getAuthorisedStoreCode() {
		return authorisedStoreCode;
	}

	public void setAuthorisedStoreCode(String authorisedStoreCode) {
		this.authorisedStoreCode = authorisedStoreCode;
	}

	public String getPrirateStoreCode() {
		return prirateStoreCode;
	}

	public void setPrirateStoreCode(String prirateStoreCode) {
		this.prirateStoreCode = prirateStoreCode;
	}

	public String getCopyStoreCode() {
		return copyStoreCode;
	}

	public void setCopyStoreCode(String copyStoreCode) {
		this.copyStoreCode = copyStoreCode;
	}

	public boolean isExistedBefore() {
		return isExistedBefore;
	}

	public void setExistedBefore(boolean isExistedBefore) {
		this.isExistedBefore = isExistedBefore;
	}

	@Override
	public String toString() {
		return "[" + this.authorisedStoreCode + "][" + this.prirateStoreCode
				+ "][" + this.copyStoreCode + "]-" + isExistedBefore;
	}
}
