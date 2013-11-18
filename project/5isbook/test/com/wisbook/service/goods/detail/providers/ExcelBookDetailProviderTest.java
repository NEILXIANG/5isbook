package com.wisbook.service.goods.detail.providers;

import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jxl.read.biff.BiffException;

import com.wisbook.service.goods.detail.GoodsDetailProvider;
import com.wisbook.service.goods.manager.AddResult;
import com.wisbook.service.goods.manager.ErrorContentException;
import com.wisbook.service.goods.manager.GoodsManager;
import com.wisbook.service.goods.manager.StoreInfo;
import com.wisbook.util.BasicTestCase;
import com.wisbook.util.FileUpload;

public class ExcelBookDetailProviderTest extends BasicTestCase {
	private GoodsDetailProvider goodsDetailProvider;
	private GoodsManager manager;

	@Resource(name = "bookManager")
	public void setManager(GoodsManager manager) {
		this.manager = manager;
	}

	@Resource(name = "excelBookDetailProvider")
	public void setGoodsDetailProvider(GoodsDetailProvider goodsDetailProvider) {
		this.goodsDetailProvider = goodsDetailProvider;
	}

	public void testExcel() throws BiffException, IOException,
			ErrorContentException {
		this.setDefaultRollback(false);
		File file = new File("E:\\_Mspace\\项目\\二手书交易平台\\5isbook\\src\\5isbook\\123.xls");
		File testFile = FileUpload.dealWithUploadFile(file);
		System.out.println(testFile.canRead() +","+testFile.getAbsolutePath()+","+testFile.getTotalSpace()/1000);
		((FileAware) goodsDetailProvider).setFile(new FileInputStream(testFile));
		AddResult result = manager.addGoods(goodsDetailProvider);
		if(result.hasError()){
			List<String> list = result.getErrorContents();
			System.out.println("-------- error isbn list ----------");
			for(String l:list){
				System.out.println(l);
			}
		}
		System.out.println("------------ success result ----------------");
		for(Map.Entry<String, StoreInfo> entry : result.getStoreInfo().entrySet()){
			System.out.println(entry.getKey() + ":" + ((StoreInfo)entry.getValue()));
		}
	}

}
