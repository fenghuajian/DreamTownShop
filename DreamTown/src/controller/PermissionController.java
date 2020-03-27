package controller;

import domain.Permission;
import com.alibaba.fastjson.JSON;
import service.IPermissionService;
import service.impl.PermissionServiceImpl;
import util.PageModel;

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
 * Servlet implementation class PermissionController
 */
@WebServlet(urlPatterns={"/permission"})
public class PermissionController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * ������������ 1����¼������֤�û��������룩 2����ȡ�˵�����
		 */
		String methodName = request.getParameter("method");
		// ���װ�
		/**
		 * ���շ������ж�
		 */
		if (methodName != null) {
			try {
				Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);// �ɱ䳤���������Ե�ͬ��������
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
	public String viewPermission(HttpServletRequest request, HttpServletResponse response){
		String currentPage = request.getParameter("currentPage");
		System.out.println("��ǰҳ��" + currentPage);
		if (currentPage == null) {
			currentPage = "1";
		}
		IPermissionService permissionService=new PermissionServiceImpl();
		PageModel<Permission> pm=permissionService.getAll(Integer.parseInt(currentPage));
		request.setAttribute("pm", pm);
		List<Permission> permissions=permissionService.getAllPermission();
		request.setAttribute("permissions", permissions);
		request.setAttribute("currentPage", currentPage);

		return "permission/viewPermission.jsp";
	}
	public void getAllPermission(HttpServletRequest request, HttpServletResponse response){
		try {
			/**
			 * ��ȡ����Ȩ��
			 */
			IPermissionService permissionService=new PermissionServiceImpl();
			List<Permission> allPermissions=permissionService.getAllPermission();

			/**
			 * ��ȡĳ����ɫ���е�Ȩ��
			 * Ҫ���õ��ý�ɫ��roleId
			 * ������Ҫ��Service�㽻������ô������PermissionService����RoleService
			 */
			String roleId=request.getParameter("roleId");
			List<Permission> ownedPermissions=permissionService.getPermissionByRoleId(roleId);

			for(Permission allPermission:allPermissions){
				for(Permission ownedPermission:ownedPermissions){
					if(ownedPermission.getPermissionid().equals(allPermission.getPermissionid())){
						allPermission.setChecked("true");
					}
				}
				if(allPermission.getIsParent().equals("true")){
					allPermission.setOpen("true");
				}
			}

			response.setContentType("application/json;charset=GBK");
			PrintWriter out=response.getWriter();
			out.write(JSON.toJSONString(allPermissions));//���������е�Ȩ�޵�checked���Ե����е�Ȩ��ת��JSON�����͸������
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void savePermission(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {

			//response.setContentType("text/html;charset=UTF-8");
			//String pName=java.net.URLDecoder.decode((String)request.getParameter("pname"),"UTF-8");
			String pSkin=request.getParameter("pSkin");//
			String pName=request.getParameter("pname");//
			pName = new String(pName.getBytes("iso-8859-1"),"utf-8");
			String isParent=request.getParameter("isParent");
			String url=request.getParameter("url");
			String pId=request.getParameter("pId");
			//System.out.println("data:"+pName+""+pSkin+""+isParent+""+""+url+pId);
			System.out.println("pname:"+pName);
			//System.out.println("pname1:"+pname);
			System.out.println("pscin:"+pSkin);
			System.out.println("pid:"+pId);
			System.out.println("url:"+url);
			System.out.println("isparent:"+isParent);
			Permission p=new Permission();
			p.setPermissionid(UUID.randomUUID().toString().replace("-", ""));
			p.setName(pName);
			p.setIconSkin(pSkin);
			p.setIsParent(isParent);
			p.setUrl(url);
			p.setPid(pId);

			IPermissionService permissionService=new PermissionServiceImpl();
			permissionService.savePermission(p);
			System.out.println(p);

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write("保存成功");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void deletePermission(HttpServletRequest request, HttpServletResponse response) {
		//根据商品id删除商品
		String permissionid=request.getParameter("id");
		System.out.println("permissionid:"+permissionid);
		IPermissionService permissionService=new PermissionServiceImpl();


		if(permissionService.deletePermission(permissionid)==1) {
			PrintWriter out=null;
			try {
				out=response.getWriter();
				out.write("OK");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(out!=null) {
					out.close();
				}
			}
		}
	}
	public void updatePermission(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {

			//response.setContentType("text/html;charset=UTF-8");
			//String pName=java.net.URLDecoder.decode((String)request.getParameter("pname"),"UTF-8");
			String pSkin=request.getParameter("pSkin");//
			String pName=request.getParameter("pname");//
			pName = new String(pName.getBytes("iso-8859-1"),"utf-8");
			String isParent=request.getParameter("isParent");
			String url=request.getParameter("url");
			String pId=request.getParameter("pId");
			//System.out.println("data:"+pName+""+pSkin+""+isParent+""+""+url+pId);
			System.out.println("pname:"+pName);
			//System.out.println("pname1:"+pname);
			System.out.println("pscin:"+pSkin);
			System.out.println("pid:"+pId);
			System.out.println("url:"+url);
			System.out.println("isparent:"+isParent);
			Permission p=new Permission();
			p.setPermissionid(UUID.randomUUID().toString().replace("-", ""));
			p.setName(pName);
			p.setIconSkin(pSkin);
			p.setIsParent(isParent);
			p.setUrl(url);
			p.setPid(pId);

			IPermissionService permissionService=new PermissionServiceImpl();
			permissionService.updatePermission(p);
			System.out.println(p);

			/*response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write("保存成功");
			out.flush();
			out.close();*/
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

