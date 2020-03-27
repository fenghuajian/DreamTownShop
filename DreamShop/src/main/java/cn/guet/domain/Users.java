package cn.guet.domain;

import java.io.Serializable;

public class Users implements Serializable {

    private String usersid;
    private String username;
    private String password;
    private String rolesid;
    private Roles role;


    public String getUsersid() {
        return usersid;
    }

    public void setUsersid(String usersid) {
        this.usersid = usersid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolesid() {
        return rolesid;
    }

    public void setRolesid(String rolesid) {
        this.rolesid = rolesid;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                "usersid='" + usersid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rolesid='" + rolesid + '\'' +
                ", role=" + role +
                '}';
    }
}