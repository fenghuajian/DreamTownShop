package service;

import bean.Permission;
import bean.Users;
import util.PageModel;

import java.util.Set;


public interface IUserService {
		void saveUser(Users user);
		Users login(String username,String password);
		Set<Permission> getPermission(String username);
		PageModel<Users> getAllUser(int currentPage);
		void saveGrant(String userId,String roleId);
		/**
		 *
		 * @param userId 根据userId来删除记录
		 * @return 返回1或0；1表示删除成功、0表示删除失败
		 */
		int deleteUser(String userId);
	}
