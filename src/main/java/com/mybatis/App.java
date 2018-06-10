package com.mybatis;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.mybatis.common.user.entity.Address;
import com.mybatis.common.user.entity.User;
import com.mybatis.common.user.enumObject.CityEnum;
import com.mybatis.common.user.mapper.UserMapper;
import com.mybatis.util.SqlSessionFactoryUtil;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) throws Exception {
    SqlSession session = SqlSessionFactoryUtil.openSession();
    // session.select("select", 38);
    /* 获取user O/R映射关系 */
    // TODO 反射相关
    UserMapper usermapper = session.getMapper(UserMapper.class);
    User aPerson;

    /* 1、增加单条记录 */
    aPerson = new User("郭灭", "123456", "1", (short) 1, new Address("湖南,长沙"), "18911110000",
        "guom@gulong.com");
    /* insert返回操作记录的数量，插入一条记录即为：1。 */
    usermapper.insert(aPerson);// 接口映射方式
    // session.insert("insert",aPerson);//直接采用DAO方式
    System.out.println("1、增加单条记录:插入用户[" + aPerson + "]成功。");

    /* 2、增加单条记录,并返回插入ID */
    aPerson = new User("高天仪", "123456", "2", (short) 2,
        new Address("湖南", CityEnum.getCityByName("株洲")), "18911110000", "gaotianyi@gulong.com");
    /*
     * xml配置了useGeneratedKeys="true" keyProperty="id"，则会将插入后的主键填充到参数里的user对象的主键中。
     * aPerson.getId()返回插入记录的主键
     */
    usermapper.insert(aPerson);
    System.out.println("2、增加单条记录,并返回插入ID:插入用户[" + aPerson + "]成功。ID：" + aPerson.getId());
    session.commit();/* 提交 */

    /* 3、读取单条记录 */
    System.out.println(aPerson.getId());
    User tmpuser = usermapper.selectById(aPerson.getId());// 接口映射方式
    // User tmpuser=session.selectOne("selectById", aPerson.getId());//直接采用DAO方式
    System.out.println("3、读取单条记录:" + tmpuser + "。ID：" + tmpuser.getId());

    /* 4、修改单条记录 */
    tmpuser.setAddress(new Address("湖南,湘潭"));
    tmpuser.setUsername("三笑将军");
    usermapper.update(tmpuser);
    session.commit();/* 提交 */
    System.out.println("4、修改单条记录:" + tmpuser + "。ID：" + tmpuser.getId());

    /* 6、查询记录 */
    List<User> userList = usermapper.findByName("灭");
    // List<User> userList=usermapper.selectAll();
    System.out.println("6、查询记录:共找到" + userList.size() + "个用户");

    /* 5、删除单条记录 */
    usermapper.delete(tmpuser.getId());
    session.commit();/* 提交 */
    System.out.println("5、删除单条记录：" + tmpuser);

    List<User> list = usermapper.selectUserAndBook(22);
    for (User m : list) {
      System.out.println(m.getArticleList());
    }
    List<User> list1 = usermapper.selectUserAndBook(22);
    for (User m : list1) {
      System.out.println(m.getArticleList());
    }
    /* End:关闭mybatis会话 */
    session.close();

  }
}
