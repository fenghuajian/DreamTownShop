package cn.guet.service;

import cn.guet.domain.Permission;
import cn.guet.domain.Shop;
import cn.guet.domain.Users;
import cn.guet.util.PageModel;

import java.util.List;
import java.util.Set;

public interface IUserService {

    // 查询所有账户
    public List<Users> findAll();

    void saveUsers(Users account);

    Users login(String username, String password);


    void saveUser(Users user);

    Set<Permission> getPermission(String username);
    PageModel<Users> getAllUser(int currentPage);
    void saveGrant(String userId, String roleId);

    int deleteUser(String userId);

    Shop getshop(String usersId);
}
