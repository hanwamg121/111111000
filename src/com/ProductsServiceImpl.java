package com;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.util.StringUtils;

public class ProductsServiceImpl implements ProductsService{
	
	@Autowired
	private HttpSolrClient client;
	
	public PageUtils search(String keyWord, String catalogName, int psort,int pageNum) {
		
		
		SolrQuery query = new SolrQuery();
		QueryResponse=client.query(query);
		//判断是否输入
		
		//1.设置q
		if(StringUtils.isEmpty(keyWord)){
			query.set("q", "*:*");//为空全部查询
		}else{
			query.set("q", "prod_name:"+keyWord);//q      prod_name:keyWord
		}
		
		
		//2.设置fq
		//2.1类别筛选
		if(!StringUtils.isEmpty(catalogName)){
			query.addFilterQuery("prod_catalog_name:"+catalogName);
		}
		//2.2价格筛选
		
		//3.排序
		int psort1=0;
		if(psort1==1){
			query.addSort("prod_price",ORDER.asc);
		}else if(psort1==2){
			query.addSort("prod_price",ORDER.desc);
		}
		
		
		//4.分页
		int rows=12;
		query.setStart(rows*(pageNum-1));
		query.setRows(rows);
				
		
		//5.回显功能 保护隐私数据 只显示需要字段
		query.setFields("prod_name","prod_catalog_name");
		
		//高亮
		query.setHighlight(true);//启动设置
		query.setFields("prod_name");//指定高亮域名
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>");
		
		
		//结果
		Map<String,Map<String,List<String>>> map=queryResponse.getHighlighting();
		
		List<products> list = queryResponse.getBeans(products.class);
		
		for(products p:list){
			Map<String,List<String>> map1=map.get(p.getPid());
			java.util.List<String> map2 = map1.get("prod_pname");
			if(map2!=null){
				p.setPname(map2.get(0));
			}
		}
		
		PageUtils page=new PageUtils(0,12,pageNum);
		return page.setList(list);
	}
	

}
