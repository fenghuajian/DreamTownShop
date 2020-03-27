package cn.guet.service.impl;



import cn.guet.dao.IPermissionDao;
import cn.guet.domain.Permission;
import cn.guet.service.IPermissionService;
import cn.guet.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements IPermissionService {
	@Autowired
	IPermissionDao permissionDao;

	@Override
	public List<Permission> getAllPermission() {
		List<Permission> p=permissionDao.getAllPermission();
		return  p;
	}
	@Override
	public List<Permission> getPermissionByRoleId(String roleId) {
		return permissionDao.getPermissionByRoleId(roleId);
	}
	@Override
	public PageModel<Permission> getAll(int currentPage) {

		PageModel<Permission> PM=permissionDao.getAll(currentPage);
		return PM;
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
		return permissionDao.deletePermission(permissionid);
	}

	@Override
	public int getAllRows() {
		return permissionDao.getPermissionNum();
	}
}