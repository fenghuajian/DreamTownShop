package service.impl;

import domain.Permission;
import dao.IPermissionDao;
import dao.imp.PermissionDaoImpl;
import service.IPermissionService;
import util.PageModel;

import java.util.List;



public class PermissionServiceImpl implements IPermissionService {

	IPermissionDao permissionDao;
	public PermissionServiceImpl(){
		permissionDao=new PermissionDaoImpl();
	}
	@Override
	public List<Permission> getAllPermission() {
		return permissionDao.getAll();
	}
	@Override
	public List<Permission> getPermissionByRoleId(String roleId) {
		return permissionDao.getPermissionByRoleId(roleId);
	}
	@Override
	public PageModel<Permission> getAll(int currentPage) {
		return permissionDao.selectAll(currentPage);
	}
	@Override
	public void savePermission(Permission p) {
		permissionDao.savePermission(p);
	}

	@Override
	public void updatePermission(Permission p) {
		permissionDao.updatePermission(p);
	}

	@Override
	public int deletePermission(String permissionid) {
		return permissionDao.delete(permissionid);
	}
}