package com.arts.org.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arts.org.webcomn.controller.CRUDController;
import com.arts.org.base.util.Util;
import com.arts.org.model.Filter;
import com.arts.org.model.entity.Articles;
import com.arts.org.model.entity.ArticlesCategory;
import com.arts.org.service.ArticlesCategoryService;
import com.arts.org.service.ArticlesService;
import com.arts.org.service.BaseService;

/**
 * 菜单暂时是写死的
 * @author meizhiwen
 *
 */
@Controller
@RequestMapping("article")
public class ArticleController extends CRUDController<Articles, Long> {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	private ArticlesService articlesService;
	
	@Autowired
	private ArticlesCategoryService articlesCategoryService;
	
	@Resource(name = "articlesService")
	@Override
	public void setBaseService(BaseService<Articles, Long> baseService) {
		this.baseService = baseService;		
	}
	
	@RequestMapping("index")
	public String index(@RequestParam(value="keyword", required=false, defaultValue="")String keyword,@RequestParam(value="categoryId", required=false, defaultValue="0")Long categoryId,@RequestParam(value="page", required=false, defaultValue="1") Integer page,ModelMap model){	
		
		
		List<Filter> filters = new ArrayList<Filter>();
		if(categoryId!=null &&categoryId>0){
			filters.add(Filter.eq("categoryId", categoryId));
		}
		if(!Util.isNullOrEmpty(keyword)){			
			filters.add(Filter.like("title", keyword));
		}
		Sort sort = new Sort(Direction.DESC,"id");
		Page<Articles> plist = this.findWithPage(page,size,filters,sort);
		if(plist!=null && plist.getContent()!=null&& plist.getContent().size()>0){
			for(Articles o :plist.getContent()){
				ArticlesCategory catagory= articlesCategoryService.find(o.getCategoryId());
				if(catagory!=null){
					 o.setCategoryName(catagory.getName());	  //设置栏目名称
				}			 
			}				
		}
		
		model.put("plist", plist);
		
		List<ArticlesCategory> categoryList = articlesCategoryService.findAll();
		model.put("categoryList", categoryList); 
		
		
		model.put("categoryId", categoryId);
		model.put("keyword", keyword);
		return "article/index";
	}
	
	@RequestMapping("detail")
	public String detail(@RequestParam(value="id", required=false, defaultValue="0") Long id,ModelMap model){
		
		Articles article = new Articles();
		if( id>0) {
			article = articlesService.queryById(id);				
		}
		model.put("article",article);
		
		List<ArticlesCategory> categoryList = articlesCategoryService.findAll();
		model.put("categoryList", categoryList); 
		
		return "article/detail";
	}
}
