package cn.itcast.controller;

import cn.itcast.domain.User;
import cn.itcast.utils.zhuanma;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * 常用的注解
 */
@Controller
@RequestMapping("/anno")
@SessionAttributes(value={"msg"})   // 把msg=美美存入到session域对中
public class AnnoController {
    /**
     * SessionAttributes的注解
     * @return
     */
    @RequestMapping(value="/testSessionAttributes")
    public String testSessionAttributes(Model model){
        System.out.println("testSessionAttributes...");
        // 底层会存储到request域对象中
        model.addAttribute("msg","美美");
        return "success";
    }

    /**
     * 获取值
     * @param modelMap
     * @return
     */
    @RequestMapping(value="/getSessionAttributes")
    public String getSessionAttributes(ModelMap modelMap){
        System.out.println("getSessionAttributes...");
        String msg = (String) modelMap.get("msg");
        System.out.println(msg);
        return "success";
    }

    /**
     * 清除
     * @param status
     * @return
     */
    @RequestMapping(value="/delSessionAttributes")
    public String delSessionAttributes(SessionStatus status){
        System.out.println("getSessionAttributes...");
        status.setComplete();
        return "success";
    }

    /*
    * jsp:<a href="anno/testRequestParam?name=哈哈">RequestParam</a>
    * */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(name="name") String username)  {
        System.out.println("执行了...");
        System.out.println(zhuanma.enCodeStr(username));
        return "success";
    }
    /*
    * 输出：showUser执行了...
        执行了...
            哈哈

    * */

    /**
     * 获取到请求体的内容
     * @return
     * jsp: <form action="anno/testRequestBody" method="post">
     *         用户姓名：<input type="text" name="username" /><br/>
     *         用户年龄：<input type="text" name="age" /><br/>
     *         <input type="submit" value="提交" />
     *     </form>
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body){
        System.out.println("执行了...");
        System.out.println(body);
        return "success";
    }
    /*
    输出：执行了...
           username=fhj&age=23
    * */

    /**
     * PathVariable注解
     * jsp:<a href="anno/testPathVariable/10">testPathVariable</a>
     * @return
     */
    @RequestMapping(value="/testPathVariable/{sid}")
    public String testPathVariable(@PathVariable(name="sid") String id){
        System.out.println("执行了...");
        System.out.println(id);
        return "success";
    }
    /*
    * 输出：执行了...
                10
    * */

    /**
     * 获取请求头的值
     * @param header
     * jsp:  <a href="anno/testRequestHeader">RequestHeader</a>
     * @return
     */
    @RequestMapping(value="/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value="Accept") String header, HttpServletRequest request,HttpServletResponse response)
            throws IOException {
        System.out.println("执行了...");
        System.out.println(header);
        // return "success";
        // response.sendRedirect(request.getContextPath()+"/anno/testCookieValue");
        return "redirect:/param.jsp";
    }


    /**
     * 获取Cookie的值
     * @return
     */
    @RequestMapping(value="/testCookieValue")
    public String testCookieValue(@CookieValue(value="JSESSIONID") String cookieValue){
        System.out.println("执行了...");
        System.out.println(cookieValue);
        return "success";
    }

    /**
     * ModelAttribute注解
     * @return
     * jsp:<form action="anno/testModelAttribute" method="post">
     *         用户姓名：<input type="text" name="uname" /><br/>
     *         用户年龄：<input type="text" name="age" /><br/>
     *         <input type="submit" value="提交" />
     *     </form>
     */
    @RequestMapping(value="/testModelAttribute")
    public String testModelAttribute(@ModelAttribute User user){
        System.out.println("testModelAttribute执行了...");
        System.out.println(user);
        return "success";
    }
    //方法会先执行
    @ModelAttribute
    public User showUser(String uname){
        System.out.println("showUser执行了...");
        // 通过用户查询数据库（模拟）
        User user = new User();
        /*user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());*/
        return user;
    }
    //@ModelAttribute
   /* public void showUser(String uname, Map<String,User> map){
        System.out.println("showUser执行了...");
        // 通过用户查询数据库（模拟）
        User user = new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        map.put("abc",user);
    }*/
    /*
    * 输出：showUser执行了...
            testModelAttribute执行了...
            User{uname='封华健', age=22, date=Sat Mar 21 15:43:10 CST 2020}
    * */







}














