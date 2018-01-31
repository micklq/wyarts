package com.arts.org.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;


import com.arts.org.model.entity.Articles;

public interface ArticlesService  extends BaseService<Articles, Long>{    

	public void clearByCategoryId(long categoryId);
}

