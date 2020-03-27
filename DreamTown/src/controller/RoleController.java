package controller;

import domain.Roles;
import service.IRoleService;
import service.impl.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

/**
 * Servlet implementation class RoleController
 */
@WebServlet(urlPatterns={"/role"})
public class RoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	public void saveGrant(HttpServletRequest request, HttpServletResponse response){
		try {
			String roleId=request.getParameter("roleId");
			String permissionIds[]=request.getParameterValues("permissionIds[]");
			IRoleService roleService=new RoleServiceImpl();
			roleService.saveGrant(roleId, permissionIds);

			response.setContentType("text/plain;charset=GBK");
			PrintWriter out=response.getWriter();
			out.write("授权成功，请重新登录");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String viewRole(HttpServletRequest request, HttpServletResponse response){

		//return之前要先去数据库把《所有角色》查询出来，并且放入某个作用域（request）
		IRoleService roleService=new RoleServiceImpl();
		List<Roles> roles=roleService.getAll();
		request.setAttribute("roles", roles);

		return "role/viewRole.jsp";
	}
	public String addRole(HttpServletRequest request, HttpServletResponse response) {

		return "role/addRole.html";
	}
	public String saveRole(HttpServletRequest request, HttpServletResponse response) {
		Roles role=new Roles();
		String roname=request.getParameter("rolename");
		try {
			roname=new String(roname.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		role.setRolesId(UUID.randomUUID().toString().replace("-", ""));
		role.setRoleName(roname);

		//System.out.println(roname);
		System.out.println(role);

		IRoleService roleService=new RoleServiceImpl();
		roleService.saveRole(role);


		// 保存用户完毕后，页面要跳转到“查看用户”
		return "role?method=viewRole";

	}

	public String grantRole(HttpServletRequest request, HttpServletResponse response){

		try {

			String roleName=request.getParameter("roleName");
			String roleId=request.getParameter("roleId");
			request.setAttribute("roleId", roleId);
			roleName=new String(roleName.getBytes("ISO-8859-1"),"GBK");
			request.setAttribute("roleName", roleName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return "role/grantUser.jsp";
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
