package com.mybatis.common.user.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 作者 E-mail：ingo
 * @version 创建时间：2016年4月17日下午6:25:27 类说明
 */
@SuppressWarnings("serial")
public class User implements Serializable {
  private Integer id;
  private String username;
  private String password;
  private String salt;
  private Short sex;
  private Address address;
  private String cellphone;
  private String email;

  private KeyAndValue gender;

  private List<Article> articleList;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public Short getSex() {
    return sex;
  }

  public void setSex(Short sex) {
    this.sex = sex;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getCellphone() {
    return cellphone;
  }

  public void setCellphone(String cellphone) {
    this.cellphone = cellphone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public KeyAndValue getGender() {
    return gender;
  }

  public void setGender(KeyAndValue gender) {
    this.gender = gender;
  }

  public List<Article> getArticleList() {
    return articleList;
  }

  public void setArticleList(List<Article> articleList) {
    this.articleList = articleList;
  }

  public User() {

  }

  public User(String username, String password, String salt, Short sex, Address address,
      String cellphone, String email) {
    super();
    this.username = username;
    this.password = password;
    this.salt = salt;
    this.sex = sex;
    this.address = address;
    this.cellphone = cellphone;
    this.email = email;
  }

  @Override
  public String toString() {
    return "姓名:" + username + "，性别：" + (gender != null ? gender.getValue() : sex) + "，地址：" + address
        + "，邮箱：" + email;
  }

}
