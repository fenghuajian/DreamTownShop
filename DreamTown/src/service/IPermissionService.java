package service;

import domain.Permission;
import util.PageModel;

import java.util.List;

public interface IPermissionService {

	List<Permission> getAllPermission();
	List<Permission> getPermissionByRoleId(String roleId);
	PageModel<Permission> getAll(int currentPage);
	void savePermission(Permission p);

    void updatePermission(Permission p);

	int deletePermission(String permissionid);
}