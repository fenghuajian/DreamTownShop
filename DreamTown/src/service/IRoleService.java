package service;

import domain.Roles;

import java.util.List;

public interface IRoleService {

	void saveRole(Roles role);
	List<Roles> getAll();
	void saveGrant(String roleId, String permissionIds[]);

    int deleteRole(String categoryid);

    int updateRoleName(String cname, String uname);
}