package com.wisbook.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页基本bean
 * @author volador
 *
 * @param <T>
 */
public class PageBean<T> {
	private int currentPage;
	private int pageSize;
	private int totalPage;
	private List<T> list;
	
	public static int PAGE_LIST_SIZE = 5;
	
	@Override
	public String toString() {
		return "当前页"+currentPage+"；每页大小："+pageSize+"；总页数："+totalPage+"；共有记录数："+list.size();
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	/*
	 * 根据currentPage,totalPage计算fromPage，toPage，并装载进pageList，供前台分页:当前页处于中间位置，页面数量的绝对值为PAGE_LIST_SIZE
	 */
	public List<Integer> getPageNumberList(){
		List<Integer> pageList=new ArrayList<Integer>();
		int shouldLeftStep=PAGE_LIST_SIZE/2;	//应该向前检索几步,5
		int realLeftStep;
		
		if((this.currentPage-1) <= shouldLeftStep){	 //实际应该向前检索的步数
			realLeftStep=this.currentPage-1;
		}else{
			realLeftStep=shouldLeftStep;
		}
		
		int shouldRightStep=PAGE_LIST_SIZE-realLeftStep-1;	//应该向后检索几步
		int realRightStep;
		
		if(this.currentPage+shouldRightStep <= this.totalPage){	//实际应该向后检索的步数
			realRightStep=shouldRightStep;
		}else{
			realRightStep=(int) (this.totalPage-this.currentPage);
		}
		
		for(int step=this.currentPage-realLeftStep-(shouldRightStep-realRightStep);step<=this.currentPage+realRightStep;step++){
			if(step>0)
			pageList.add(step);
		}
		
		return pageList;
	}
}
