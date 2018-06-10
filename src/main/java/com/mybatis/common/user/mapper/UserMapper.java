package com.mybatis.common.user.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.mybatis.common.user.entity.User;

public interface UserMapper {
  /**
   * 新增用戶
   * 
   * @param user
   * @return
   * @throws Exception
   */
  public int insert(User user) throws Exception;

  /**
   * 修改用戶
   * 
   * @param user
   * @param id
   * @return
   * @throws Exception
   */
  public int update(User user) throws Exception;

  /**
   * 刪除用戶
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public int delete(int id) throws Exception;

  /**
   * 根据id查询用户信息
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public User selectById(int id) throws Exception;

  /**
   * 根据idList查询用户信息
   * 
   * @param idList
   * @return
   * @throws Exception
   */
  public List<User> selectByIdList(@Param(value = "idList") List<Integer> idList) throws Exception;

  /**
   * 查询所有的用户信息
   * 
   * @return
   * @throws Exception
   */
  public List<User> selectAll() throws Exception;

  /**
   * 查询所有的用户信息
   * 
   * @return
   * @throws Exception
   */
  public List<User> selectUserAndBook(Integer userId) throws Exception;

  /**
   * 根据用户名模糊查询用户
   * 
   * @param searchkey
   * @return
   * @throws Exception
   */
  public List<User> findByName(@Param(value = "searchkey") String searchkey) throws Exception;

  /**
   * 根据id区间模糊查询用户
   * 
   * @param searchkey
   * @return
   * @throws Exception
   */
  public List<User> searchByIdMinAndMax(Map<String, Integer> searchMap) throws Exception;

  /**
   * 根据id区间模糊查询用户
   * 
   * @param searchkey
   * @return
   * @throws Exception
   */
  public List<User> searchByInnerClassProperties(User user) throws Exception;
}
