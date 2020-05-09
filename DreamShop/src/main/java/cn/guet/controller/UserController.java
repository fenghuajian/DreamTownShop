package cn.guet.controller;

import cn.guet.domain.Permission;
import cn.guet.domain.Users;
import cn.guet.service.IUserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

        @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response,
                        String username,String password) {
            System.out.println(username+password);
        Users user = userService.login(username, password);
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()   + path + "/";
            System.out.println("path:"+path);
            HttpSession session = request.getSession();
            session.setAttribute("path",basePath);
            session.setAttribute("user", user);
       /* Shop shop= userService.getshop(user.getUsersId());
        String shopid=null;*/
        System.out.println("USER:"+user);
       // System.out.println("shop:"+shop);

      /*  if(shop != null){
            shopid=shop.getShopid();
            session.setAttribute("shop", shop);
            session.setAttribute("shopid", shopid);
        }*/
        return "index";
    }
    //
@RequestMapping("/getMenu")
    public void getMenu(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            System.out.println(username);
            Set<Permission> permission = userService.getPermission(username);
            response.setContentType("application/json;charset=UTF-8");
           // System.out.println(permission);
            //System.out.println("json: " + JSON.toJSONString(permission));
            PrintWriter out = response.getWriter();
            out.print(JSON.toJSONString(permission));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
