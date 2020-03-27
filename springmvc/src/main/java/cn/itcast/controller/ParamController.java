package cn.itcast.controller;

import cn.itcast.domain.Account;
import cn.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 请求参数绑定
 */
@Controller
@RequestMapping("/param")
public class ParamController {

    /**
     * 请求参数绑定入门
     * @return
     * jsp：a href="param/testParam?username=hehe&password=123">请求参数绑定</a>
     */
    @RequestMapping("/testParam")
    public String testParam(String username,String password){
        System.out.println("执行了...");
        System.out.println("用户名："+username);
        System.out.println("密码："+password);
        return "success";
    }

    /**
     * 请求参数绑定把数据封装到JavaBean的类中
     * Account:多了引用对象：  private User user;
     *                      private List<User> list;
     *                      private Map<String,User> map;
     * jsp:<form action="param/saveAccount" method="post">
     *     姓名：<input type="text" name="username" /><br/>
     *     密码：<input type="text" name="password" /><br/>
     *     金额：<input type="text" name="money" /><br/>
     *     用户姓名：<input type="text" name="user.uname" /><br/>
     *     用户年龄：<input type="text" name="user.age" /><br/>
     *     用户2姓名：<input type="text" name="list[0].uname" /><br/>
     *     用户2年龄：<input type="text" name="list[0].age" /><br/>
     *     用户姓名：<input type="text" name="list[1].uname" /><br/>
     *     用户年龄：<input type="text" name="list[1].age" /><br/>
     *     用户3姓名：<input type="text" name="map['one'].uname" /><br/>
     *     用户3年龄：<input type="text" name="map['one'].age" /><br/>
     *     用户姓名：<input type="text" name="map['tow'].uname" /><br/>
     *     用户年龄：<input type="text" name="map['tow'].age" /><br/>
     *     用户姓名：<input type="text" name="map['th'].uname" /><br/>
     *     用户年龄：<input type="text" name="map['th'].age" /><br/>
     *     <input type="submit" value="提交" />
     * </form>
     * @return
     */
    @RequestMapping("/saveAccount")
    public String saveAccount(Account account){
        System.out.println("执行了...");
        System.out.println(account);
        return "success";
    }
    /*
    * 输出：执行了...
        Account{username='fhj', password='fhj1234', money=300.0, user=User{uname='秦琼先', age=23},
        list=[User{uname='法国红', age=21}, User{uname='低功耗', age=34}],
        map={one=User{uname='阿斯蒂芬', age=23}, th=User{uname='的身份', age=45}, tow=User{uname='回家看看', age=43}}}
    * */

/*
     * 自定义类型转换器
     * @param user
     * @return
     */
    @RequestMapping("/saveUser")
    public String saveUser(User user){
        System.out.println("执行了...");
        System.out.println(user);
        return "success";
    }
    /**

    /**
     * 原生的API
     * @return
     */
    @RequestMapping("/testServlet")
    public String testServlet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("执行了...");
        System.out.println("request输出:"+request);

        HttpSession session = request.getSession();
        System.out.println("session输出："+session);

        ServletContext servletContext = session.getServletContext();
        System.out.println("servletContext输出："+servletContext);

        System.out.println(response);
        return "success";
    }

}
