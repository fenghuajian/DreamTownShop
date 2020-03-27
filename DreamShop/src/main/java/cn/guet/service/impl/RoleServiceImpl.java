package cn.guet.service.impl;



import cn.guet.dao.IRoleDao;
import cn.guet.domain.Roles;
import cn.guet.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	IRoleDao roleDao;


	@Override
	public void saveRole(Roles role) {
		roleDao.saveRole(role);
	}

	/*public RoleServiceImpl(){
            roleDao=new RoleDaoImpl();
        }*/
	@Override
	public List<Roles> getAll() {
		return roleDao.findAll();
	}
	@Override
	public void saveGrant(String roleId, String[] permissionIds) {
		roleDao.saveGrant(roleId, permissionIds);
	}
}