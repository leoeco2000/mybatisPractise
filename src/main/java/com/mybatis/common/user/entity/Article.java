package com.mybatis.common.user.entity;

import java.io.Serializable;

public class Article implements Serializable {
  private Integer userId;

  private String name;

  private static final long serialVersionUID = 1L;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }
}
