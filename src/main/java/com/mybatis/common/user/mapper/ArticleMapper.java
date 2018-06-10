package com.mybatis.common.user.mapper;

import com.mybatis.common.user.entity.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
}