package cn.guet.controller;

import cn.guet.domain.Permission;
import cn.guet.service.IPermissionService;
import cn.guet.util.PageModel;
import com.alibaba.fastjson.JSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.ServletException;

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
 * Servlet implementation class PermissionServlet
 */
@Controller
@RequestMapping("permission")
public class PermissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IPermissionService permissionService;


	@RequestMapping("/viePermission")
	public String viewPermission(HttpServletRequest request, HttpServletResponse response){
		String currentPage = request.getParameter("currentPage");
		System.out.println("当前页面" + currentPage);
		if (currentPage == null) {
			currentPage = "1";
		}
		//获取总行数
		int totalRows=permissionService.getAllRows();
		//总页数


		PageModel<Permission> pm=permissionService.getAll(Integer.parseInt(currentPage));
		request.setAttribute("pm", pm);
		List<Permission> permissions=permissionService.getAllPermission();
		request.setAttribute("permissions", permissions);
		request.setAttribute("currentPage", currentPage);

		return "permission/viewPermission";
	}
	@RequestMapping("/getAllPermission")
	public void getAllPermission(HttpServletRequest request, HttpServletResponse response){
		try {
			List<Permission> allPermissions=permissionService.getAllPermission();
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
			out.write(JSON.toJSONString(allPermissions));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@RequestMapping("/savePermission")
	public void savePermission(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {

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
	@RequestMapping("/deletePermission")
	public void deletePermission(HttpServletRequest request, HttpServletResponse response) {
		//根据商品id删除商品
		String permissionid=request.getParameter("id");
		System.out.println("permissionid:"+permissionid);


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
	@RequestMapping("/updatePermission")
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

