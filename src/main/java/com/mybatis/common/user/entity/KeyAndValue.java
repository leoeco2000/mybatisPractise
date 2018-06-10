package com.mybatis.common.user.entity;

import java.io.Serializable;

public class KeyAndValue implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int key;
  private String value;

  private KeyAndValue(int key, String value) {
    this.key = key;
    this.value = value;
  }

  private KeyAndValue() {

  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
