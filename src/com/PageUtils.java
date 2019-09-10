package com;

import java.util.List;





public class PageUtils {
	private int pageSize=5;
	private int currentPage=5;
	private int offset;
	private int count;
	private int countSize=5;
	private List<?> list;
	
	public PageUtils(int count,int pageSize,int currentPage){
		if(pageSize>1){
			this.pageSize=pageSize;
		}else{
			this.pageSize=5;
		}
		if(currentPage>1){
			this.currentPage=currentPage;
		}else{
			this.currentPage=1;
		}
		
	}
	public PageUtils(){
		
	}
	public void setList(List<?> list){
		
	}
	
}
