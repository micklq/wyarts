package com.arts.org.data.repository; 

import javax.annotation.Resource;

import com.arts.org.model.entity.Articles;



@Resource(name = "articlesRepository")
public interface ArticlesRepository  extends BaseRepository<Articles, Long>{

}

