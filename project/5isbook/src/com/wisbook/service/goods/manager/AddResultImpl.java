package com.wisbook.service.goods.manager;

import java.util.List;
import java.util.Map;

public class AddResultImpl implements AddResult {

	private Map<String,StoreInfo> storeInfo;
	private List<String> errorMessages;
	private List<String> errorContents;
	
	public void setStoreInfo(Map<String, StoreInfo> storeInfo) {
		this.storeInfo = storeInfo;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public void setErrorContents(List<String> errorContents) {
		this.errorContents = errorContents;
	}

	@Override
	public Map<String, StoreInfo> getStoreInfo() {
		return storeInfo;
	}

	@Override
	public List<String> getErrorMessages() {
		return errorMessages;
	}

	@Override
	public List<String> getErrorContents() {
		return errorContents;
	}

	@Override
	public boolean hasError() {
		if(this.errorContents != null && this.errorContents.size() > 0) return true;
		return false;
	}

}
