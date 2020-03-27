package cn.itcast.domain;

import java.io.Serializable;

/**
 * 帐户
 */
public class Account implements Serializable{

    private Integer id;
    private Integer userid;
    private Double money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
