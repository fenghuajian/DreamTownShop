package controller;

import domain.Category;
import service.ICategoryService;
import service.IUserService;
import service.impl.CategoryServiceImpl;
import service.impl.UserServiceImpl;
import util.PageModel;
import util.UUIDString;

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

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns={"/category"})
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IUserService userService = new UserServiceImpl();
	ICategoryService categoryService=new CategoryServiceImpl();

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

	/**
	 * 删除商品类别
	 * @param request
	 * @param response
	 * @return
	 */
	public String deleteCategory(HttpServletRequest request, HttpServletResponse response) {
		String categoryid=request.getParameter("categoryId");
		if(categoryService.deleteCategory(categoryid)==1){
			return "category?method=viewCategory";
		}else{
			request.setAttribute("errorMsg", "删除失败，请联系维护人员");
			return "error.jsp";
		}
	}

	/**
	 * 修改商品类别
	 * @param request
	 * @param response
	 */
	public void  updateCategory(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String cname=request.getParameter("cname");
		String uname=request.getParameter("uname");
		try {
			cname=new String(cname.getBytes("ISO-8859-1"),"UTF-8");
			uname=new String(uname.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("cname:"+cname+"......"+"uname:"+uname);
		if(categoryService.updateCategory(cname,uname)==1){
			//System.out.println("1111");
			out.print("OK");
			out.flush();
			out.close();

		}else{
			//System.out.println("00000");
			out.print("NO");
			out.flush();
			out.close();
		}

	}


	/**
	 * 显示类别
	 * @param request
	 * @param response
	 * @return
	 */

	public String viewCategory(HttpServletRequest request, HttpServletResponse response) {

		String currentPage = request.getParameter("currentPage");
		System.out.println("当前页：" + currentPage);
		if (currentPage == null) {
			currentPage = "1";
		}

		ICategoryService categoryService=new CategoryServiceImpl();

		PageModel<Category> pm = categoryService.getAllCategory(Integer.parseInt(currentPage));
		//System.out.println();
		//int total=categoryService.gettotalPage();
		request.setAttribute("pm", pm);// pm携带了两个数据：1、真实的数据；2、总页数
		request.setAttribute("currentPage", currentPage);

		return "category/viewCategory.jsp";

	}

	/**
	 * 保存类别
	 * @param request
	 * @param response
	 * @return
	 */
	public String saveCategory(HttpServletRequest request, HttpServletResponse response) {
		Category category=new Category();
		category.setCategoryid(UUIDString.getId());
		//category.setCategoryid(UUID.randomUUID().toString().replace("-", ""));
		String name=request.getParameter("name");
		try {
			name=new String(name.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		category.setName(name);
		System.out.println(category);
		ICategoryService categoryService=new CategoryServiceImpl();
		categoryService.saveCategory(category);

		// 保存用户完毕后，页面要跳转到“查看类别”
		return "category?method=viewCategory";

	}

	public String addCategory(HttpServletRequest request, HttpServletResponse response) {

		return "category/addCategory.html";
	}





	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}

