package service.impl;

import domain.Roles;
import dao.IRoleDao;
import dao.imp.RoleDaoImpl;
import service.IRoleService;

import java.util.List;



public class RoleServiceImpl implements IRoleService {


	IRoleDao roleDao=null;
	public RoleServiceImpl(){

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

	@Override
	public int deleteRole(String roleid) {
		return roleDao.deleteRole(roleid);
	}

	@Override
	public int updateRoleName(String cname, String uname) {
		return roleDao.updateRoleName(cname,uname);
	}
}