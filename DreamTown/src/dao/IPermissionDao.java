package dao;

import domain.Permission;

import java.util.List;

public interface IPermissionDao extends IBaseDao<Permission>{

	List<Permission> getPermissionByRoleId(String roleId);

    void savePermission(Permission p);

    void updatePermission(Permission p);
}