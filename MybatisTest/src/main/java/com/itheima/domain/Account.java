package com.itheima.domain;

import java.io.Serializable;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 */
public class Account implements Serializable {

    private Integer id;
    private Integer userid;
    private Double money;

    //从表实体应该包含一个主表实体的对象引用
    private Myuser myuser;

    public Myuser getUser() {
        return myuser;
    }

    public void setUser(Myuser user) {
        this.myuser = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userid=" + userid +
                ", money=" + money +
                '}';
    }
}
