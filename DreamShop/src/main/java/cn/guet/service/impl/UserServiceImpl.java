package cn.guet.service.impl;

import cn.guet.dao.IUserDao;
import cn.guet.domain.Permission;
import cn.guet.domain.Shop;
import cn.guet.domain.Users;
import cn.guet.service.IUserService;
import cn.guet.util.PageModel;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Set;

@Service("userService")
@Transactional
public class UserServiceImpl  implements IUserService {

    @Autowired
    private IUserDao userDao;

    public List<Users> findAll() {
        System.out.println("业务层：查询所有账户...");
        return userDao.findAll();
    }


    @Override
    public void saveUsers(Users account) {
        System.out.println("业务层：保存账户...");

            userDao.saveUser(account);
            int i=1/0;
            //System.out.println(i);

        userDao.findAll();
    }

    @Override
    public Users login(String username, String password) {
        return userDao.login(username,password);
    }

    @Override
    public void saveUser(Users user) {

    }

    @Override
    public Set<Permission> getPermission(String username) {
        return userDao.getPermission(username);
    }

    @Override
    public PageModel<Users> getAllUser(int currentPage) {
        return null;
    }

    @Override
    public void saveGrant(String userId, String roleId) {

    }

    @Override
    public int deleteUser(String userId) {
        return 0;
    }

    @Override
    public Shop getshop(String usersId) {
        return null;
    }


}
