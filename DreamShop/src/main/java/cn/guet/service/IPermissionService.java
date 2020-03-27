package cn.guet.service;



import cn.guet.domain.Permission;
import cn.guet.util.PageModel;

import java.util.List;

public interface IPermissionService {

	List<Permission> getAllPermission();
	List<Permission> getPermissionByRoleId(String roleId);
	PageModel<Permission> getAll(int currentPage);
	void savePermission(Permission p);

    void updatePermission(Permission p);

	int deletePermission(String permissionid);

	int getAllRows();
}