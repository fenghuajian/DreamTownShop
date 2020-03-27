package test;

import domain.Permission;
import dao.IPermissionDao;
import dao.IRoleDao;
import dao.imp.PermissionDaoImpl;
import dao.imp.RoleDaoImpl;
import org.junit.Test;

import java.util.UUID;

public class PermissionTestPermission {
	
	
	@Test
	public void testAddPermission(){
		/*
		 * ����Dao��
		 * ���ھ�
		 * RoleDao
		 */
		Permission permission=new Permission();
		permission.setPermissionid((UUID.randomUUID().toString().replace("-","")));
		permission.setPid("ad52d6bb1db74f0ea99ad93bf9a3696a");
		permission.setName("权限管理");
		permission.setUrl(null);
		permission.setIcon(null);
		permission.setIsParent("true");
		
		IPermissionDao permissionDao=new PermissionDaoImpl();
		permissionDao.save(permission);
		
	}
	@Test
	public void testAddViewPermission(){
		/*
		 * ����Dao��
		 * ����Permission����Ȼ�����IPermissionDao��save����
		 */
		Permission permission=new Permission();
		permission.setPermissionid((UUID.randomUUID().toString().replace("-","")));
		permission.setPid("19c33eb3909e450a91476731739df5c0");
		permission.setName("查看权限");
		permission.setUrl("viewPermission");
		permission.setIcon(null);
		permission.setIsParent("false");
		
		IPermissionDao permissionDao=new PermissionDaoImpl();
		permissionDao.save(permission);
		System.out.println(permission);
	}
	@Test
	public void addPermission(){
		/*
		 * ����Dao��
		 * ����Permission����Ȼ�����IPermissionDao��save����
		 */
		Permission permission=new Permission();
		permission.setPermissionid((UUID.randomUUID().toString().replace("-","")));
		permission.setPid("19c33eb3909e450a91476731739df5c0");
		permission.setName("添加权限");
		permission.setUrl("addPermission");
		permission.setIcon(null);
		permission.setIsParent("false");
		
		IPermissionDao permissionDao=new PermissionDaoImpl();
		permissionDao.save(permission);
	}
	@Test
	public void testGetRole(){
		IRoleDao roleDao=new RoleDaoImpl();
		//Roles role=roleDao.get("8656f2d56d3a49e49481cbf975a01e81");
		//System.out.println("角色名："+role.getRoleName());
	}
	
}