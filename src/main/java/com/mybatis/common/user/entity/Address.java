package com.mybatis.common.user.entity;

import java.io.Serializable;
import com.mybatis.common.user.enumObject.CityEnum;

public class Address implements Serializable {
  /**
  * 
  */
  private static final long serialVersionUID = 1L;
  String province;
  CityEnum city;

  public Address() {}

  public Address(String province, CityEnum city) {
    this.province = province;
    this.city = city;
  }

  // 假设我们存储在db中的字符串是以","号分隔省市关系的
  public Address(String address) {
    if (address != null) {
      String[] segments = address.split(",");
      if (segments.length > 1) {
        this.province = segments[0];
        this.city = CityEnum.getCityByName(segments[1]);
      } else if (segments.length > 0) {
        this.city = CityEnum.getCityByName(segments[0]);
      }
    }
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public CityEnum getCity() {
    return city;
  }

  public void setCity(CityEnum city) {
    this.city = city;
  }

  @Override
  public String toString() {
    return this.province + "," + this.city.getName();
  }

}
