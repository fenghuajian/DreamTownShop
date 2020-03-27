package com.itheima.dao;

import com.itheima.domain.Myuser;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 *
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     *  @Select("select * from myuser")
     */

    /**
     * 查询所有用户
     * @return
     */
    List<Myuser> findAll();

    /**
     * 保存用户
     * @param user
     */
    void saveUser(Myuser user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(Myuser user);

    /**
     * 根据Id删除用户
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    Myuser findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     * @param username
     * @return
     */
    List< Myuser> findByName(String username);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();
    /**
     * 根据传入参数条件
     * @param user 查询的条件：有可能有用户名，有可能有性别，也有可能有地址，还有可能是都有
     * @return
     */
    List<Myuser> findUserByCondition(Myuser user);

    /**
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
   // List<Myuser> findUserByVo(QueryVo vo);

    List<Myuser> findUserInIds(QueryVo vo);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);
}
