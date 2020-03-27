package service.impl;

import domain.Roles;
import dao.IRoleDao;
import dao.imp.RoleDaoImpl;
import service.IRoleService;

import java.util.List;



public class RoleServiceImpl implements IRoleService {


	IRoleDao roleDao=null;
	public RoleServiceImpl(){
		//���ģʽ֮���󹤳�ģʽ
		roleDao=new RoleDaoImpl();
	}

	@Override
	public void saveRole(Roles role) {
		roleDao.save(role);
	}

	/*public RoleServiceImpl(){
            roleDao=new RoleDaoImpl();
        }*/
	@Override
	public List<Roles> getAll() {
		return roleDao.getAll();
	}
	@Override
	public void saveGrant(String roleId, String[] permissionIds) {
		roleDao.saveGrant(roleId, permissionIds);
	}
}