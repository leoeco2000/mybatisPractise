package com.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.mybatis.common.user.entity.Address;
import com.mybatis.common.user.entity.User;
import com.mybatis.common.user.mapper.UserMapper;
import com.mybatis.util.SqlSessionFactoryUtil;

/**
 * Hello world!
 *
 */
public class App2 {
  public static void main(String[] args) throws Exception {
    SqlSession session = SqlSessionFactoryUtil.openSession();
    /* 获取user O/R映射关系 */
    // TODO 反射相关
    UserMapper usermapper = session.getMapper(UserMapper.class);
    User aPerson;

    Map<String, Integer> searchMap = new HashMap<String, Integer>();
    searchMap.put("minId", 20);
    searchMap.put("maxId", 30);
    List<User> userList = usermapper.searchByIdMinAndMax(searchMap);
    System.out.println(userList.size());

    aPerson = new User("郭灭", "123456", "1", (short) 1, new Address("湖南,长沙"), "18911110000",
        "guom@gulong.com");

    userList = usermapper.searchByInnerClassProperties(aPerson);
    System.out.println(userList.size());

    List<Integer> idList = new ArrayList<Integer>();
    idList.add(30);
    idList.add(32);
    userList = usermapper.selectByIdList(idList);
    System.out.println(userList.size());

    User tmpuser = usermapper.selectById(22);// 接口映射方式
    System.out.println("3、读取单条记录:" + tmpuser + "。ID：" + tmpuser.getId());

    List<User> list = usermapper.selectUserAndBook(22);
    for (User m : list) {
      System.out.println(m.getArticleList());
    }

    /* End:关闭mybatis会话 */
    session.close();
  }
}
