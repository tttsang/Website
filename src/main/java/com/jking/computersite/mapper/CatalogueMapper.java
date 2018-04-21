package com.jking.computersite.mapper;

import com.jking.computersite.entity.Article;
import com.jking.computersite.entity.Catalogue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

  public interface CatalogueMapper {
    int insert(Catalogue record);

    int insertSelective(Catalogue record);

    List<Article> find(@Param("first_level") String first_level, @Param("second_level") String second_level, @Param("start") int start, @Param("size") int size);

    int count(@Param("first_level") String first_level, @Param("second_level") String second_level);
}