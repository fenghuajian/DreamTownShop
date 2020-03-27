package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 */
public interface IRoleDao {
    /**
     * 查询所有角色
     */
    @Select(" select * FROM ROLE")
    @Results(id="roletMap",value = {
            @Result(id=true,column = "roleid",property = "roleid"),
            @Result(column = "rolename",property = "rolename"),
            @Result(column = "roledesc",property = "roledesc"),
            @Result(property = "myuser",column = "roleid",one=@One(select="com.itheima.dao.IUserDao.findByRid",fetchType= FetchType.EAGER))
    })
    List<Role> findAll();
    @Select("  select * from role where roleid in(select roleid from user_role\n" +
            "                 where userid=#{uid})")
    @ResultMap("roletMap")
    List<Role> findRolesByUid(int uid);
}
