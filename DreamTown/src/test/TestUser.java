package test;

import bean.Users;
import dao.IUserDao;
import dao.imp.UserDaoImpl;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.UUID;

public class TestUser extends TestCase {

	@Test
	public void testLogin(){
		IUserDao userDao=new UserDaoImpl();
		//Users user=userDao.login("wangwu", "ww1234");//用户----角色----权限
		Users user=userDao.login("fhj", "fhj1234");//用户----角色----权限
		System.out.println(user);
		//this.assertEquals(3, user.getRole().getPermissions().size());//第一个参数：期待的值；第二个参数：actual实际的值

	}
	
	@Test
	public void testAddUser(){
		/*
		 * ����Dao��
		 * ���ھ�
		 * RoleDao
		 */
		Users user=new Users();
		user.setUsersId((UUID.randomUUID().toString().replace("-","")));
		user.setUsername("fhj");
		user.setPassword("fhj1234");
		user.setRolesId("969323d6ff9a4acf8aabc8d367474d14");
		
		IUserDao userDao=new UserDaoImpl();
		userDao.save(user);
	}
	@Test
	public void testAddUser01(){
		/*
		 * ����Dao��
		 * ���ھ�
		 * RoleDao
		 */
		Users user=new Users();
		user.setUsersId((UUID.randomUUID().toString().replace("-","")));
		user.setUsername("byy");
		user.setPassword("byy1234");
		user.setRolesId("8656f2d56d3a49e49481cbf975a01e81");
		
		IUserDao userDao=new UserDaoImpl();
		userDao.save(user);
	}
	
}