package controller;

import domain.Collection;
import domain.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.IProductService;
import service.impl.ProductServiceImpl;
import controller.base.BaseServlet;
import util.PageModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/product")
public class ProductController extends BaseServlet {

	private static final long serialVersionUID = 1L;

	//把商店id送回前台
	public void getShop(HttpServletRequest request, HttpServletResponse response) {
		String shopid=null;
	    shopid=(String) request.getSession().getAttribute("shopid");
		System.out.println("shopid:"+shopid);
		PrintWriter out;
		try {
			if(shopid !=null)
			{
				out=response.getWriter();
				out.write(shopid);
				out.flush();
				out.close();
			}
			else
			{
				out=response.getWriter();
				out.write("no");
				out.flush();
				out.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listProduct(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out=null;
		try {
			String currentPage=request.getParameter("currentPage");
			if (currentPage == null) {
				currentPage = "1";
			}
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd")
					.create();
			IProductService productService = new ProductServiceImpl();
			PageModel<Product> pm = productService.getAllProduct(Integer.parseInt(currentPage));

			response.setContentType("application/json;charset=UTF-8");
			System.out.println(gson.toJson(pm));

			out = response.getWriter();
			out.write(gson.toJson(pm));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null) {
				out.close();
			}
		}
	}

	public void updateProduct(HttpServletRequest request, HttpServletResponse response) {
		String pid = null;
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}


		//真实路径
		String realPath = this.getServletContext().getRealPath("/upload");
		//存放路径
		String path="C:\\Users\\封华健\\IdeaProjects\\DreamTown\\web\\img";
		Product pro=new Product();
		//pro.setProductId(UUID.randomUUID().toString().replace("-", ""));
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);// 检查输入请求是否为multipart表单数据。

		if (isMultipart == true) {//表示表单要上传文件
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。
			// 执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			Iterator<FileItem> itr = items.iterator();//items集合中包含了（商品名称、要上传的文件图片）

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				// 检查当前项目是普通表单项目还是上传文件。
				if (item.isFormField()) {// 如果是普通表单项目，显示表单内容。
					String fieldName = item.getFieldName();
					if(fieldName.equals("categoryId")) {
						pro.setCategoryId(item.getString());
						System.out.println("类别id："+item.getString());
					}
					if(fieldName.equals("productid")) {
						pid = item.getString();
						pro.setProductId(pid);
						System.out.println("商品id："+item.getString());
					}
					else if (fieldName.equals("name")) {
						try {
							String name = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
							System.out.println("商品名称："+name);
							pro.setName(name);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					else if (fieldName.equals("shopname")) {
						try {
							String shopname = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
							System.out.println("商店名称："+shopname);
							pro.setShopname(shopname);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					else if("price".equals(fieldName)){
						pro.setPrice(Float.parseFloat(item.getString()));
						System.out.println("商品价格："+Float.parseFloat(item.getString()));
					}
					else if(fieldName.equals("onlineDate")){
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						Date date;
						try {
							date = sdf.parse(item.getString());
							pro.setOnlineDate(new java.sql.Date(date.getTime()));
							System.out.println("出厂日期："+new java.sql.Date(date.getTime()));

						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					else if(fieldName.equals("descInfo")){
						try {
							pro.setDescInfo(new String(item.getString().getBytes("ISO-8859-1"),"UTF-8"));
							System.out.println("商品详情："+new String(item.getString().getBytes("ISO-8859-1"),"UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					else if(fieldName.equals("isJingXuan")){
						pro.setIsJingXuan(item.getString());
						System.out.println("是否精选："+item.getString());
					}
					else if(fieldName.equals("isReMai")){
						pro.setIsReMai(item.getString());
						System.out.println("是否热卖："+item.getString());
					}
					else if(fieldName.equals("isXiaJia")){
						pro.setIsXiaJia(item.getString());
						System.out.println("是否下架："+item.getString());
					}
				} else {// 如果是上传文件，显示文件名。
					File fullFile = new File(item.getName());

					System.out.println("上传的文件名："+item.getName());
					File savedFile = new File(realPath + "\\", fullFile.getName());
					File saved = new File(path + "\\", fullFile.getName());
					System.out.println("保存的文件名："+saved.getName());
					try {
						/*item.write(savedFile);
						item.write(saved);*/
					} catch (Exception e) {
						e.printStackTrace();
					}
					pro.setPicURL(saved.getName());
				}
			}
			IProductService productService=new ProductServiceImpl();
			//System.out.println("添加的商品id:" + pro.getProductId());

				//System.out.println("商品1："+pro);

				productService.update(pro);
				System.out.println("商品："+pro);

			PrintWriter out;
			try {
				out=response.getWriter();
				out.write("OK");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.print("the enctype must be multipart/form-data");
		}


		}
	public void addProduct(HttpServletRequest request, HttpServletResponse response) {

		String pid = null;
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		//真实路径
		String realPath = this.getServletContext().getRealPath("/upload");
		//存放路径
		String path="C:\\Users\\封华健\\IdeaProjects\\DreamTown\\web\\img";
		Product pro=new Product();
		pro.setProductId(UUID.randomUUID().toString().replace("-", ""));
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);// 检查输入请求是否为multipart表单数据。

		if (isMultipart == true) {//表示表单要上传文件
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。
			// 执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			Iterator<FileItem> itr = items.iterator();//items集合中包含了（商品名称、要上传的文件图片）

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				// 检查当前项目是普通表单项目还是上传文件。
				if (item.isFormField()) {// 如果是普通表单项目，显示表单内容。
					String fieldName = item.getFieldName();
					if(fieldName.equals("categoryId")) {
						pro.setCategoryId(item.getString());
						System.out.println("类别id："+item.getString());
					}
					if(fieldName.equals("productid")) {
						pid = item.getString();
						System.out.println("商品id："+item.getString());
					}
					else if (fieldName.equals("name")) {
						try {
							String name = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
							System.out.println("商品名称："+name);
							pro.setName(name);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					else if (fieldName.equals("shopname")) {
						try {
							String shopname = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
							System.out.println("商店名称："+shopname);
							pro.setShopname(shopname);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					else if("price".equals(fieldName)){
						pro.setPrice(Float.parseFloat(item.getString()));
						System.out.println("商品价格："+Float.parseFloat(item.getString()));
					}
					else if(fieldName.equals("onlineDate")){
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						Date date;
						try {
							date = sdf.parse(item.getString());
							pro.setOnlineDate(new java.sql.Date(date.getTime()));
							System.out.println("出厂日期："+new java.sql.Date(date.getTime()));

						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					else if(fieldName.equals("descInfo")){
						try {
							pro.setDescInfo(new String(item.getString().getBytes("ISO-8859-1"),"UTF-8"));
							System.out.println("商品详情："+new String(item.getString().getBytes("ISO-8859-1"),"UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					else if(fieldName.equals("isJingXuan")){
						pro.setIsJingXuan(item.getString());
						System.out.println("是否精选："+item.getString());
					}
					else if(fieldName.equals("isReMai")){
						pro.setIsReMai(item.getString());
						System.out.println("是否热卖："+item.getString());
					}
					else if(fieldName.equals("isXiaJia")){
						pro.setIsXiaJia(item.getString());
						System.out.println("是否下架："+item.getString());
					}
				} else {// 如果是上传文件，显示文件名。
					File fullFile = new File(item.getName());

					System.out.println("上传的文件名："+item.getName());
					File savedFile = new File(realPath + "\\", fullFile.getName());
					File saved = new File(path + "\\", fullFile.getName());
					System.out.println("保存的文件名："+saved.getName());
					try {
						item.write(savedFile);
						item.write(saved);
					} catch (Exception e) {
						e.printStackTrace();
					}
					pro.setPicURL(saved.getName());
				}
			}
			IProductService productService=new ProductServiceImpl();
			System.out.println("添加的商品id:" + pro.getProductId());
			productService.save(pro);
			PrintWriter out;
			try {
				out=response.getWriter();
				out.write("OK");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.print("the enctype must be multipart/form-data");
		}
	}

	public void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		//根据商品id删除商品
		String productId=request.getParameter("id");
		System.out.println("删除商品");
		IProductService productService = new ProductServiceImpl();
		if(productService.deleteProduct(productId)==1) {
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

	public void showProduct(HttpServletRequest request, HttpServletResponse response) {

		String productid=request.getParameter("productid");
		IProductService productService = new ProductServiceImpl();
		Product pro=productService.SelectProductById(productid);

		response.setContentType("application/json;charset=utf-8");
		PrintWriter out;
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.create();
		System.out.println(gson.toJson(pro));
		try {
			out = response.getWriter();
			out.print(gson.toJson(pro));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 可能还想购买
	 * @param request
	 * @param response
	 */
	public void showOtherproduct(HttpServletRequest request, HttpServletResponse response) {
		String productid=request.getParameter("productid");
		IProductService productService = new ProductServiceImpl();
		List<Product> prolist=productService.getOther(productid);
		Iterator<Product> itp=prolist.iterator();
		while(itp.hasNext()) {
			Product p=itp.next();
			System.out.println(p.getProductId());
			if(p.getProductId().equals(productid))
			{

				itp.remove();
			}
		}
		List<Product> prolist1= new ArrayList<Product>();
		//只显示四个
		for (int i=0;i<prolist.size();i++)
		{
			if (i>=4)
			{
				break;
			}
			prolist1.add(prolist.get(i));
		}

		response.setContentType("application/json;charset=utf-8");
		PrintWriter out;
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.create();
		try {
			out = response.getWriter();
			out.print(gson.toJson(prolist1));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*public String addCar1(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out=null;


		String productId=request.getParameter("productid");
		String customerId=(String) request.getSession().getAttribute("customerId");
		System.out.println(productId);

		IProductService productService = new ProductServiceImpl();
		productService.addCar(productId,customerId);
		return "success.html?productid="+productId;
	}*/
	public void addCar1(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out=null;

		try {
			String productId=request.getParameter("productId");
			String customerId=(String) request.getSession().getAttribute("customerId");
			System.out.println("pid:"+productId);
			System.out.println("cid:"+customerId);
			//request.getSession().setAttribute("username", "shit");
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd")
					.create();
			if(customerId !=null)
			{
				IProductService productService = new ProductServiceImpl();
				productService.addCar(productId,customerId);

				response.setContentType("application/json;charset=UTF-8");

				out = response.getWriter();
				out.write(gson.toJson("ok"));
				//out.write(num);
				out.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null) {
				out.close();
			}
		}
	}

	/**
	 * 把商品加入购物车
	 * @param request
	 * @param response
	 */
	public void addCar(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out=null;

		try {

			String productId=request.getParameter("productId");
			String customerId=(String) request.getSession().getAttribute("customerId");
			System.out.println("pid:"+productId);
			System.out.println("cid:"+customerId);
			//request.getSession().setAttribute("username", "shit");
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd")
					.create();
			if(customerId !=null)
			{
				IProductService productService = new ProductServiceImpl();
				productService.addCar(productId,customerId);
				response.setContentType("application/json;charset=UTF-8");
				out = response.getWriter();
				out.write(gson.toJson("OK"));
				out.flush();
			}
			else{
				out = response.getWriter();
				out.write(gson.toJson("您好，请您先登录，谢谢！"));
				out.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null) {
				out.close();
			}
		}
	}

	/**
	 * 删除收藏
	 */

	public void deleteCollection(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out=null;
		try {

			String productId=request.getParameter("productId");
			String customerId=(String) request.getSession().getAttribute("customerId");
			System.out.println("pid:"+productId);
			System.out.println("cid:"+customerId);

			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd")
					.create();

			IProductService productService = new ProductServiceImpl();
			productService.deleteCollection(productId,customerId);
			response.setContentType("application/json;charset=UTF-8");
				out = response.getWriter();
				out.write(gson.toJson("OK"));
				out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null) {
				out.close();
			}
		}
	}

	/**
	 * 加入收藏
	 */

	public void addCollection(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out=null;
		try {

			String productId=request.getParameter("productid");
			String customerId=(String) request.getSession().getAttribute("customerId");
			System.out.println("pid:"+productId);
			System.out.println("cid:"+customerId);

			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd")
					.create();

				IProductService productService = new ProductServiceImpl();
				int flag=productService.addCollection(productId,customerId);
				response.setContentType("application/json;charset=UTF-8");
				if(flag==1){

					out = response.getWriter();
					out.write(gson.toJson("OK"));
					out.flush();
				}

				else{
					out = response.getWriter();
					out.write(gson.toJson("no"));
					out.flush();
				}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null) {
				out.close();
			}
		}
	}

    /**
     * 模糊查询搜索商品显示
     * @param request
     * @param response
     */
	public void viewProduct1(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out=null;
		try {
			String currentPage=request.getParameter("currentPage");
			String name=request.getParameter("categoryId");
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			//String productId=request.getParameter("productId");
			System.out.println(name);
			System.out.println(currentPage);
			//System.out.println(productId);
			if (currentPage == null) {
				currentPage = "1";
			}
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd")
					.create();
			IProductService productService = new ProductServiceImpl();
			PageModel<Product> pm = productService.getProduct1(Integer.parseInt(currentPage),name);
			pm.setCurrentPage(Integer.parseInt(currentPage));

			response.setContentType("application/json;charset=UTF-8");
			System.out.println("pm:"+pm);
			out = response.getWriter();
			out.write(gson.toJson(pm));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null) {
				out.close();
			}
		}
	}
	//显示收藏
	public void viewCollection(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out=null;
		try {
			String currentPage=request.getParameter("cu");
			String customerid=request.getParameter("id");
			System.out.println(customerid);
			System.out.println(currentPage);
			//System.out.println(productId);
			if (currentPage == null) {
				currentPage = "1";
			}
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd")
					.create();
			IProductService productService = new ProductServiceImpl();
			PageModel<Product> pm = productService.viewCollection(Integer.parseInt(currentPage),customerid);
			pm.setCurrentPage(Integer.parseInt(currentPage));
			response.setContentType("application/json;charset=UTF-8");
			System.out.println("pm:"+gson.toJson(pm));
			out = response.getWriter();
			out.write(gson.toJson(pm));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null) {
				out.close();
			}
		}
	}

    /**
     * 拿到热卖商品
     * @param request
     * @param response
     */
	public void viewProductRemai(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out=null;
		try {

			String name=request.getParameter("categoryId");
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			//String productId=request.getParameter("productId");
			System.out.println(name);

			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd")
					.create();
			IProductService productService = new ProductServiceImpl();
			List<Product> pm = productService.getProductRemai(name);
			response.setContentType("application/json;charset=UTF-8");
			System.out.println("pm:"+pm);
			out = response.getWriter();
			out.write(gson.toJson(pm));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null) {
				out.close();
			}
		}
	}

	/**
	 * 热卖1.0代码
	 * @param request
	 * @param response
	 */
    public void viewProduct2(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out=null;
        try {
            String currentPage=request.getParameter("currentPage");
            String name=request.getParameter("categoryId");
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            //String productId=request.getParameter("productId");
            System.out.println(name);
            System.out.println(currentPage);
            //System.out.println(productId);
            if (currentPage == null) {
                currentPage = "1";
            }
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();
            IProductService productService = new ProductServiceImpl();
            PageModel<Product> pm = productService.getProduct2(Integer.parseInt(currentPage),name);
            pm.setCurrentPage(Integer.parseInt(currentPage));

            response.setContentType("application/json;charset=UTF-8");
            System.out.println("pm:"+pm);
            out = response.getWriter();
            out.write(gson.toJson(pm));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out!=null) {
                out.close();
            }
        }
    }
	public void viewProduct(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out=null;
		try {
			String currentPage=request.getParameter("currentPage");
			String categoryId=request.getParameter("categoryId");
			String productId=request.getParameter("productId");
			System.out.println(categoryId);
			System.out.println(currentPage);
			System.out.println(productId);
			if (currentPage == null) {
				currentPage = "1";
			}
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd")
					.create();
			IProductService productService = new ProductServiceImpl();
			PageModel<Product> pm = productService.getProduct(Integer.parseInt(currentPage),categoryId);
			pm.setCurrentPage(Integer.parseInt(currentPage));

			response.setContentType("application/json;charset=UTF-8");

			out = response.getWriter();
			out.write(gson.toJson(pm));
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