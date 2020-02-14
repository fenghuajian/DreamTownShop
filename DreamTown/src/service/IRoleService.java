package service;

import bean.Roles;

import java.util.List;

public interface IRoleService {

	void saveRole(Roles role);
	List<Roles> getAll();
	void saveGrant(String roleId,String permissionIds[]);
}