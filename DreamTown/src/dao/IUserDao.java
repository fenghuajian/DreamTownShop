package dao;

import bean.Permission;
import bean.Users;

import java.util.Set;

public interface IUserDao extends IBaseDao<Users> {
	Users login(String username,String password);
	Set<Permission> getPermission(String username);
	void saveGrant(String userId, String roleId);
}
