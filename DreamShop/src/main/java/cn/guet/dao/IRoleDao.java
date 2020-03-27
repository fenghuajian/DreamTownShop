package cn.guet.dao;

import cn.guet.domain.Roles;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {
    @Autowired
    // 查询所有角色
    @Select("select * from roles")
     List<Roles> findAll();

    /**
     * 授权，分两步
     * 1、先删除原来的权限
     * 2、把数组中的perimissionid和拿到的roleid依次加入到 rolespermission中
     *
     *
     */
    @Select("SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT * FROM  ROLES) t WHERE rownum<=#{endRow}) WHERE rn>=#{startRow}")
    List<Roles> findPage(@Param("startRow") int startRow, @Param("endRow") int endRow);
    @Delete("DELETE FROM rolespermission WHERE rolesid=#{rolesid}")

    void deleteRolesPermission(String rolesid);
    @Insert("INSERT INTO rolespermission(rolesid,permissionid) VALUES(#{roleid},#{permissionid})")
    void SaveGrant(@Param("roleid") String roleid, @Param("permissionid") String permissionid);
    void saveGrant(String roleId, String permissionIds[]);

    /**
     * 保存角色
     * @param role
     */
    @Insert("INSERT INTO ROLES(rolesid,rolename) values(#{rolesId},#{roleName})")
    void saveRole(Roles role);

}
