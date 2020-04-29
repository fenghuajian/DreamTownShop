package com.tarena.elts.entity;

import java.io.Serializable;
/**
 * 用户实体
 * 此类的作用是一个对象用于描述某一个用户的信息
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;//用户的编号
	private String name;//用户姓名属性
	private String password;//密码
	private String phone;//用户的手机号电话号码
	private String email;//用户的电子邮箱
	
	/**
	 * 默认的构造方法
	 */
	public User(){
		super();
	}
	
	/**
	 * 代参数的构造方法
	 * @param name
	 * @param id
	 * @param password
	 */
	public User(String name,int id,String password){
		super();
		this.id = id;
		this.password = password;
	}

	/**
	 * 覆盖toString()方法,返回用户的姓名
	 */
	public String toString(){
		return name;
	}
	
	/**
	 * 覆盖equals()方法
	 * 比较内容，若两个id一样，两个对象内容一致
	 */
	public boolean equals(Object obj){
		if(obj==null)
			return false;
		if(this==obj)
			return true;
		if(obj instanceof User){
			User other = (User)obj;
			return this.id == other.id;
		}
		return false;
	}
	
	/**
	 * 重写hashCode()方法
	 */
	public int hashCode(){
		return id;
	}
	
	/**
	 *生成属性的公开方法 
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}