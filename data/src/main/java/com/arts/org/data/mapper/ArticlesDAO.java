package com.arts.org.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.arts.org.model.entity.Articles;

public interface ArticlesDAO
{

	void clearByCategoryId(@Param("categoryId")long categoryId);



}       

