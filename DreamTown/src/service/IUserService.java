package service;

import domain.Permission;
import domain.Shop;
import domain.Users;
import util.PageModel;

import java.util.Set;


public interface IUserService {
		void saveUser(Users user);
		Users login(String username, String password);
		Set<Permission> getPermission(String username);
		PageModel<Users> getAllUser(int currentPage);
		void saveGrant(String userId, String roleId);

		int deleteUser(String userId);

    	Shop getshop(String usersId);

    String getRoleId(String userid);
}
