package cn.guet.dao;

import cn.guet.domain.Permission;
import cn.guet.domain.Shop;
import cn.guet.domain.Users;
import org.apache.ibatis.annotations.*;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    List<Users> findAll();
    /*

    /**
     *拿到分页数据
     * @param startRow
     * @param endRow
     * @return
     */
    @Select("SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT * FROM  users) t WHERE rownum<=#{endRow}) WHERE rn>=#{startRow}")
    List<Users> findPage(@Param("startRow") int startRow,@Param("endRow") int endRow);
    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into users(usersid,username,password,rolesid) values(#{usersid},#{username},#{password},#{rolesid})")
    void saveUser(Users user);


    /**
     * 登录
     */
    @Select("SELECT * FROM users where username=#{username} and password=#{password}")
    Users login(@Param("username") String username,@Param("password") String password);
    /*
    * 根据用户名拿到权限列表
    * */
    @Select("SELECT p.* FROM permission p,rolespermission rp\n" +
            " WHERE rp.permissionid=p.permissionid \n" +
            " AND rp.rolesid=(select rolesid FROM users WHERE username=#{username})")
    Set<Permission> getPermission(@Param("username") String username);
    /*
    * 授权
    * */
    @Update("UPDATE users SET rolesid=#{roleid} WHERE usersid=#{userid}")
    void saveGrant( @Param("userid") String userId, @Param("roleid") String roleId);
    //Shop getShop(String userId);

    /**
     * 获取表中所有行
     * @return
     */
    @Select("select count(*) from users")
    int  getCount();

    /**
     * 根据用户id删除用户信息
     * @param userId
     * @return
     */
    @Delete("delete from users where usersid=#{userid}")
    void deleteUser(@Param("userid") String userId);

    /**
     * 根据用户拿到商店信息
     * @param usersId
     * @return
     */
    @Select("select * from shop where customerid=#{userid}")
    Shop getshop(@Param("userid")String usersId);



}
