package com;

public interface ProductsService {
	/*
	 * 主页查询方法（全文检索）
	 * @param keyWord 主页关键字检索
	 * @param catalogName 类别
	 * @param price 价格
	 * @param psort 排序
	 * */
	public PageUtils search(String keyWord,String catalogName,int psort,int pageNum);
	
	
	
}
