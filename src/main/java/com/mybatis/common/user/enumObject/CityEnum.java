package com.mybatis.common.user.enumObject;

public enum CityEnum {
  ChangSha(4301, "长沙"), Zhuzhou(4302, "株洲"), Xiangtan(4303, "湘潭");

  int value;
  String name;

  private CityEnum(int value, String name) {
    this.value = value;
    this.name = name;
  }

  public int getValue() {
    return this.value;
  }

  public String getName() {
    return this.name;
  }

  /* 方法getCityByName是为了typeHandler后加的 */
  public static CityEnum getCityByName(String name) {
    for (CityEnum city : CityEnum.values()) {
      // System.out.println(CityEnum.name);
      // System.out.println(CityEnum.name());
      if (city.name.equals(name)) {
        return city;
      }
    }
    throw new IllegalArgumentException("无效的value值: " + name + "!");
  }
}
