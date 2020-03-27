package test;

import domain.Roles;
import dao.IRoleDao;
import dao.imp.RoleDaoImpl;
import org.junit.Test;

import java.util.UUID;

public class TestRole {
	
	@Test
	public void testAdmin(){
		/*
		 * ����Dao��
		 * ���ھ�
		 * RoleDao
		 */
		Roles role=new Roles();
		role.setRolesId((UUID.randomUUID().toString().replace("-","")));
		role.setRoleName("管理员");
		
		IRoleDao roleDao=new RoleDaoImpl();
		roleDao.save(role);
		System.out.println(role);
		
	}
	@Test
	public void testNormal(){
		/*
		 * ����Dao��
		 * ���ھ�
		 * RoleDao
		 */
		Roles role=new Roles();
		role.setRolesId((UUID.randomUUID().toString().replace("-","")));
		role.setRoleName("普通用户");
		
		IRoleDao roleDao=new RoleDaoImpl();
		roleDao.save(role);
		
	}
	@Test
	public void testGetRole(){
		IRoleDao roleDao=new RoleDaoImpl();
	//	Roles role=roleDao.get("8656f2d56d3a49e49481cbf975a01e81");
	//	System.out.println("角色名"+role.getRoleName());
	}
	
}