package test;

import bean.Permission;
import dao.IPermissionDao;
import dao.imp.PermissionDaoImpl;
import org.junit.Test;

import java.util.UUID;

public class PermissionTest {
	
	@Test
	public void testAddRootPermission(){
		/*
		 * ����Dao��
		 * ����Permission����Ȼ�����IPermissionDao��save����
		 * 商城后台管理：ad52d6bb1db74f0ea99ad93bf9a3696a
		 */

		Permission permission=new Permission();
		permission.setPermissionid(UUID.randomUUID().toString().replace("-",""));
		permission.setPid(null);
		permission.setName("商城后台管理");
		permission.setUrl(null);
		permission.setIcon(null);
		permission.setIsParent("true");
		
		IPermissionDao permissionDao=new PermissionDaoImpl();
		permissionDao.save(permission);
		System.out.println(permission.getName());
	}
	@Test
	public  void out(){

		System.out.println("封华健");
	}
	@Test
	public void testAddUserPermission(){
		/*
		 * ����Dao��
		 * ����Permission����Ȼ�����IPermissionDao��save����
		 * 用户管理：f64272bf34194986b6e3ff666531df5b
		 */
		Permission permission=new Permission();
		permission.setPermissionid(UUID.randomUUID().toString().replace("-",""));
		permission.setPid("ad52d6bb1db74f0ea99ad93bf9a3696a");
		permission.setName("用户管理");
		permission.setUrl(null);
		permission.setIcon(null);
		permission.setIsParent("true");
		
		IPermissionDao permissionDao=new PermissionDaoImpl();
		permissionDao.save(permission);
	}
	@Test
	public void testAddRolePermission(){
		/*
		 * ����Dao��
		 * ����Permission����Ȼ�����IPermissionDao��save����
		 * 角色管理：c6fa1d2e95e84cf7aa48bee5ffa4ce9a
		 */
		Permission permission=new Permission();
		permission.setPermissionid(UUID.randomUUID().toString().replace("-",""));
		permission.setPid("ad52d6bb1db74f0ea99ad93bf9a3696a");
		permission.setName("角色管理");
		permission.setUrl(null);
		permission.setIcon(null);
		permission.setIsParent("true");

		IPermissionDao permissionDao=new PermissionDaoImpl();
		permissionDao.save(permission);
	}

	@Test
	public void testAddRole(){
		/*
		 * ����Dao��
		 * ����Permission����Ȼ�����IPermissionDao��save����
		 * 添加角色：8ad9d3c6a434417bbd45150be217c739
		 */
		Permission permission=new Permission();
		permission.setPermissionid(UUID.randomUUID().toString().replace("-",""));
		permission.setPid("c6fa1d2e95e84cf7aa48bee5ffa4ce9a");
		permission.setName("添加角色");
		permission.setUrl("addRole");
		permission.setIcon(null);
		permission.setIsParent("false");

		IPermissionDao permissionDao=new PermissionDaoImpl();
		permissionDao.save(permission);
	}
	@Test
	public void testviewRole(){
		/*
		 * ����Dao��
		 * ����Permission����Ȼ�����IPermissionDao��save����
		 * 查看角色：2f7df5c7ea784305833327b9e8aee129
		 */
		Permission permission=new Permission();
		permission.setPermissionid(UUID.randomUUID().toString().replace("-",""));
		permission.setPid("c6fa1d2e95e84cf7aa48bee5ffa4ce9a");
		permission.setName("查看角色");
		permission.setUrl("viewRole");
		permission.setIcon(null);
		permission.setIsParent("false");

		IPermissionDao permissionDao=new PermissionDaoImpl();
		permissionDao.save(permission);
		System.out.println(permission);
	}
	@Test
	public void testAddViewUserPermission(){
		/*
		 * ����Dao��
		 * ����Permission����Ȼ�����IPermissionDao��save����
		 * 添加用户：5d6e00a4c03140168369630eb5490787
		 */
		Permission permission=new Permission();
		permission.setPermissionid(UUID.randomUUID().toString().replace("-",""));
		permission.setPid("f64272bf34194986b6e3ff666531df5b");
		permission.setName("添加用户");
		permission.setUrl("addUser");
		permission.setIcon(null);
		permission.setIsParent("false");
		
		IPermissionDao permissionDao=new PermissionDaoImpl();
		permissionDao.save(permission);
		System.out.println(permission);
	}
	@Test
	public void testAddchakanUserPermission(){
		/*
		 * ����Dao��
		 * ����Permission����Ȼ�����IPermissionDao��save����
		 * 查看用户：c189d716dbc54b919c92ff41639c8358
		 */
		Permission permission=new Permission();
		permission.setPermissionid(UUID.randomUUID().toString().replace("-",""));
		permission.setPid("f64272bf34194986b6e3ff666531df5b");
		permission.setName("查看用户");
		permission.setUrl("viewUser");
		permission.setIcon(null);
		permission.setIsParent("false");

		IPermissionDao permissionDao=new PermissionDaoImpl();
		permissionDao.save(permission);
		System.out.println(permission);
	}
}