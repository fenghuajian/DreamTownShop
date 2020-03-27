package service.impl;

import domain.Permission;
import domain.Roles;
import domain.Shop;
import domain.Users;
import dao.IRoleDao;
import dao.IUserDao;
import dao.imp.RoleDaoImpl;
import dao.imp.UserDaoImpl;
import service.IUserService;
import util.PageModel;

import java.util.List;
import java.util.Set;

public class UserServiceImpl implements IUserService {

	IUserDao userDao=null;
	IRoleDao roleDao=null;
	public UserServiceImpl(){
		//设计模式之抽象工厂模式
		userDao=new UserDaoImpl();
		roleDao=new RoleDaoImpl();
	}
	@Override
	public void saveUser(Users user) {
		userDao.save(user);//调用的BaseDao的save方法

	}

	@Override
	public Users login(String username, String password) {//感觉Service层（业务逻辑层）完全没有必要存在
		return userDao.login(username, password);
	}
	@Override
	public Set<Permission> getPermission(String username) {
		return userDao.getPermission(username);
	}
	@Override
	public PageModel<Users> getAllUser(int currentPage) {
		//PageModel包含了真实的Users的数据
		PageModel<Users> pm=userDao.selectAll(currentPage);
		List<Users> users=pm.getList();
		for(Users user:users){
			Roles role=roleDao.getById(user.getRolesId());
			user.setRole(role);//用户关联角色
		}
		return pm;
	}
	@Override
	public void saveGrant(String userId, String roleId) {
		userDao.saveGrant(userId, roleId);
	}
	@Override
	public int deleteUser(String userId) {
		return userDao.delete(userId);
	}

	@Override
	public Shop getshop(String usersId) {
		return userDao.getShop(usersId);
	}
}