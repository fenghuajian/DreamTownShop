package com.itheima.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 */
public class Role implements Serializable {

    private Integer roleid;
    private String rolename;
    private String roledesc;

    //多对多的关系映射：一个角色可以赋予多个用户
    private List<Myuser> myusers;

    public List<Myuser> getMyuser() {
        return myusers;
    }

    public void setMyuser(List<Myuser> myusers) {
        this.myusers = myusers;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleid=" + roleid +
                ", rolename='" + rolename + '\'' +
                ", roledesc='" + roledesc + '\'' +
                '}';
    }
}
