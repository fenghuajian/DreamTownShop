package cn.guet.dao;


import cn.guet.domain.Customer;
import cn.guet.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ICustomerDao {
	/**
	 * 判断用户是否存在
	 * @param username
	 * @return
	 */
	@Select("select count(*) from customer where username=#{username}")
	int isExist(String username);
	/*
	* 验证用户并返回用户id
	* */
	@Select("select customerid from customer where username=#{username} and password=#{password}")
	String verify(@Param("username") String customerName,@Param("password") String password);

	/**
	 * 更改用户信息
	 * @param customer
	 */
	@Update("update customer set username='${username}',defaultname='${defaultname}',password='${password}'," +
			" phone='${phone}',defaultphone='${defaultphone}',defaultaddr='${defaultaddr}',mailbox='${mailbox}' " +
			" where customerid='${customerid}'")
	void updateCustomer(Customer customer);

	/**
	 * 添加商店信息
	 * @param customerid
	 * @param shopid
	 * @param shopname
	 */
	@Insert("insert into shop values (#{customerid},#{shopid},#{shopname})")
    void addShop(@Param("customerid") String customerid,@Param("shopid") String shopid, @Param("shopname") String shopname);

	/**
	 * 修改用户的角色
	 * @param userid
	 * @param roleid
	 */
	@Update("update users set rolesid=#{rolesid} where usersid=#{usersid}")
    void updaterole(@Param("usersid") String userid, @Param("rolesid") String roleid);

	/**
	 * 保存用户
	 * @param userid
	 * @param username
	 * @param password
	 */
	@Insert("insert into users(usersid,username,password) values(#{usersid},#{username},#{password})")
	void saveUser(@Param("usersid") String userid, @Param("username") String username,@Param("password") String password);

	/**
	 * 保存客户
	 */
	@Insert("insert into customer(customerid,username,password,phone,mailbox,defaultname,defaultphone,defaultaddr) " +
			" values('${customerid}','${username}','${password}','${phone}','${mailbox}','${defaultname}','${defaultphone}','${defaultaddr}')")
	void save(Customer customer);

	/**
	 * 根据用户id查询用户
	 * @param customerId
	 * @return
	 */
	@Select("select * from customer where customerid=#{customerid}")
	Customer getById(String customerId);
	/**
	 * 根据id删除
	 */
	@Delete("delete from customer where customerid=#{customerid}")
	void delete(String customerid);

	public Set<Permission> getPermission(String username);
}