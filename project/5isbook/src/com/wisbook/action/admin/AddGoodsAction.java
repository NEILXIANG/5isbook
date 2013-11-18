package com.wisbook.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.service.goods.detail.GoodsDetailProvider;
import com.wisbook.service.goods.detail.providers.FileAware;
import com.wisbook.service.goods.manager.AddResult;
import com.wisbook.service.goods.manager.ErrorContentException;
import com.wisbook.service.goods.manager.GoodsManager;
import com.wisbook.service.goods.manager.StoreInfo;

/**
 * @author Ban 通过上传的Excel文件来增加商品
 * 
 */
@Component("addGoods")
@Scope("prototype")
public class AddGoodsAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private GoodsManager goodsManager;
	private GoodsDetailProvider goodsDetailProvider;
	// 上传文件
	private File file;
	private String fileContentType;
	private String fileFileName;
	private List<String> errorIsbnList;
	private List<String> errorMessage;
	private Map<String, StoreInfo> entrys;
	private boolean hasError;

	/**
	 * 上传Excel文件，然后根据文件增加商品
	 * 
	 * @throws ErrorContentException
	 */
	public String execute() throws IOException, ErrorContentException {
		((FileAware) goodsDetailProvider).setFile(new FileInputStream(file));
		AddResult result = goodsManager.addGoods(goodsDetailProvider);
		entrys = result.getStoreInfo();
		hasError = result.hasError();
		if (hasError) {
			errorIsbnList = (List<String>) result.getErrorContents();
			errorMessage = result.getErrorMessages();
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (file == null)
			this.addFieldError("file", "文件不能为空");
		else  if (!fileFileName.endsWith(".xls"))
			this.addFieldError("file", "文件一定要为EXCEL文件，而且需要是 *.xls 的格式");
		
		System.out.println(this.fileContentType);
	}

	public void setEntrys(Map<String, StoreInfo> entrys) {
		this.entrys = entrys;
	}

	@Resource(name = "bookManager")
	public void setGoodsManager(GoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	@Resource(name = "excelBookDetailProvider")
	public void setGoodsDetailProvider(GoodsDetailProvider goodsDetailProvider) {
		this.goodsDetailProvider = goodsDetailProvider;
	}

	public GoodsManager getGoodsManager() {
		return goodsManager;
	}

	public GoodsDetailProvider getGoodsDetailProvider() {
		return goodsDetailProvider;
	}

	public List<String> getErrorIsbnList() {
		return errorIsbnList;
	}

	public void setErrorIsbnList(List<String> errorIsbnList) {
		this.errorIsbnList = errorIsbnList;
	}

	public List<String> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public Map<String, StoreInfo> getEntrys() {
		return entrys;
	}

}
