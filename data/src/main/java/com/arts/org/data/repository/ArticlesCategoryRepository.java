package com.arts.org.data.repository; 

import javax.annotation.Resource;

import com.arts.org.model.entity.ArticlesCategory;



@Resource(name = "articlesCategoryRepository")
public interface ArticlesCategoryRepository  extends BaseRepository<ArticlesCategory, Long>{

}

