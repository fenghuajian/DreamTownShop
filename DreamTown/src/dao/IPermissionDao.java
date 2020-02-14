package dao;

import bean.Permission;

import java.util.List;

public interface IPermissionDao extends IBaseDao<Permission>{

	List<Permission> getPermissionByRoleId(String roleId);
}