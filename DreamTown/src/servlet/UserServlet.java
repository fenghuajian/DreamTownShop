package servlet;

import bean.Permission;
import bean.Roles;
import bean.Users;
import com.alibaba.fastjson.JSON;
import service.IRoleService;
import service.IUserService;
import service.impl.RoleServiceImpl;
import service.impl.UserServiceImpl;
import util.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns={"/user"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IUserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		/**
		 * 处理两种请求： 1、登录请求（验证用户名和密码） 2、获取菜单请求
		 */
		String methodName = request.getParameter("method");
		// 简易版
		/**
		 * 按照方法来判断
		 */
		if (methodName != null) {
			try {
				Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,
						HttpServletResponse.class);// 可变长参数：可以等同看作数组
				String url = (String) method.invoke(this, request, response);
				if (url != null) {
					request.getRequestDispatcher(url).forward(request, response);
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					request.getRequestDispatcher("500Error.jsp").forward(request, response);
				} catch (ServletException | IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			try {
				request.getRequestDispatcher("500Error.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}

	}
	public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
		String userId=request.getParameter("userId");
		if(userService.deleteUser(userId)==1){
			return "user?method=viewUser";
		}else{
			request.setAttribute("errorMsg", "删除失败，请联系维护人员");
			return "error.jsp";
		}
	}
	public String  saveGrant(HttpServletRequest request, HttpServletResponse response) {
		String roleId=request.getParameter("roleId");
		String userId=request.getParameter("userId");
		IUserService userService = new UserServiceImpl();
		userService.saveGrant(userId, roleId);
		return "user?method=viewUser";
	}
	public String grantUser(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("username");
		String userId=request.getParameter("userId");
		//i.所有权限（把所有角色查询出来，并且显示在授权页面中）
		IRoleService roleService=new RoleServiceImpl();
		List<Roles> roles=roleService.getAll();
		request.setAttribute("roles", roles);
		request.setAttribute("username", username);
		request.setAttribute("userId", userId);
		return "user/userGrant.jsp";
		//拿到所有角色后，要把角色的集合放入request作用域

		//ii.把被授权的用户名也要显示在页面上


	}
	public String viewUser(HttpServletRequest request, HttpServletResponse response) {

		String currentPage = request.getParameter("currentPage");
		System.out.println("当前页：" + currentPage);
		if (currentPage == null) {
			currentPage = "1";
		}
		IUserService userService = new UserServiceImpl();
		PageModel<Users> pm = userService.getAllUser(Integer.parseInt(currentPage));

		request.setAttribute("pm", pm);// pm携带了两个数据：1、真实的数据；2、总页数
		request.setAttribute("currentPage", currentPage);

		return "user/viewUser.jsp";

	}

	public String saveUser(HttpServletRequest request, HttpServletResponse response) {
		Users user = new Users();
		user.setUsersId(UUID.randomUUID().toString().replace("-", ""));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		System.out.println(user);
		IUserService userService = new UserServiceImpl();
		userService.saveUser(user);

		// 保存用户完毕后，页面要跳转到“查看用户”
		return "user?method=viewUser";

	}

	public String addUser(HttpServletRequest request, HttpServletResponse response) {

		return "user/addUser.html";
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		IUserService userService = new UserServiceImpl();
		Users user = userService.login(username, password);
		System.out.println("USER:"+user);

		// 放入session中
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		return "index.jsp";
	}
	public String customerLogin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("u:"+username);
		System.out.println("p:"+password);
		/*IUserService userService = new UserServiceImpl();
		Users user = userService.login(username, password);
		System.out.println("USER:"+user);

		// 放入session中
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		return "index.jsp";*/
		return "ok";
	}

	public void getMenu(HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = request.getParameter("username");

			IUserService userService = new UserServiceImpl();
			Set<Permission> permission = userService.getPermission(username);

			response.setContentType("application/json;charset=UTF-8");
			System.out.println("json: " + JSON.toJSONString(permission));
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(permission));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}

