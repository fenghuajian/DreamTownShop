package dao;

import domain.Permission;
import domain.Shop;
import domain.Users;

import java.util.Set;

public interface IUserDao extends IBaseDao<Users> {
	Users login(String username, String password);
	Set<Permission> getPermission(String username);
	void saveGrant(String userId, String roleId);
	Shop getShop(String userId);

    String getRoleId(String userid);

    void saveCustomer(Users user);

	int deleteCustomer(String userId);

	int SimpleUpdateUser(String uid, String uname, String pwd);
}
