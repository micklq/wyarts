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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arts.org.webcomn.RespBody;
import com.arts.org.webcomn.controller.CRUDController;
import com.arts.org.webcomn.util.WebUtil;
import com.arts.org.base.util.Util;
import com.arts.org.model.Filter;
import com.arts.org.model.entity.Articles;
import com.arts.org.model.entity.ArticlesCategory;
import com.arts.org.model.entity.Department;
import com.arts.org.model.entity.Permission;
import com.arts.org.model.entity.Role;
import com.arts.org.model.entity.UserPassport;
import com.arts.org.service.ArticlesCategoryService;
import com.arts.org.service.ArticlesService;
import com.arts.org.service.BaseService;
import com.arts.org.service.PermissionService;
import com.arts.org.service.RoleService;

/**
 * 菜单暂时是写死的
 * @author meizhiwen
 *
 */
@Controller
@RequestMapping("articleCategory")
public class ArticleCategoryController extends CRUDController<ArticlesCategory, Long> {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleCategoryController.class);
	
	@Autowired
	private ArticlesCategoryService articlesCategoryService;
	
	@Autowired
	private ArticlesService articlesService;
	
	@Resource(name = "articlesCategoryService")
	@Override
	public void setBaseService(BaseService<ArticlesCategory, Long> baseService) {
		this.baseService = baseService;		
	}
	
	@RequestMapping("index")
	public String index(@RequestParam(value="page", required=false, defaultValue="1") Integer page,ModelMap model){	
		
		 //List<ArticlesCategory> list = this.findWithAll();
		 //model.put("list", list); 
		 
		 List<ArticlesCategory> departTree  = WebUtil.getArticlesCategoryTree(this.findWithAll());
			if(departTree!=null && departTree.size()>0){
				for(ArticlesCategory o :departTree){				
					if(o.getDepth()>1){
						String pre ="";
						for(int i=1; i<o.getDepth(); i++){
						 	pre +="├";
						}
						o.setName(pre+o.getName());
					}
				}
			}	
			model.put("list",departTree);	
		 
		return "articleCategory/index";
	}
	
	@RequestMapping("detail")
	public String detail(@RequestParam(value="id", required=false, defaultValue="0") Long id,ModelMap model){		
		
		ArticlesCategory category = new ArticlesCategory();
		if( id>0) {
			category = articlesCategoryService.queryById(id);				
		}
		model.put("category",category);
		
		return "articleCategory/detail";
	}
	
	@RequestMapping( value = "remove", method= RequestMethod.POST)
	@ResponseBody
	public RespBody remove(@RequestParam(value="id", required=false, defaultValue="0") Long id){	
		
		 if (id > 0)
         {
			 ArticlesCategory item = articlesCategoryService.queryById(id);
             if (item == null)
             {
            	  return respBodyWriter.toError("数据不存在", 500);
             }             
             articlesService.clearByCategoryId(id); //分类下资讯所属栏目归0
             articlesCategoryService.delete(id);
             return respBodyWriter.toSuccess();             
         }
         return respBodyWriter.toError("参数错误", 400);         
		   			
	}
}
