package service;

import bean.Permission;
import bean.Shop;
import bean.Users;
import util.PageModel;

import java.util.Set;


public interface IUserService {
		void saveUser(Users user);
		Users login(String username, String password);
		Set<Permission> getPermission(String username);
		PageModel<Users> getAllUser(int currentPage);
		void saveGrant(String userId, String roleId);
		/**
		 *
		 * @param userId ����userId��ɾ����¼
		 * @return ����1��0��1��ʾɾ���ɹ���0��ʾɾ��ʧ��
		 */
		int deleteUser(String userId);

    Shop getshop(String usersId);
}
