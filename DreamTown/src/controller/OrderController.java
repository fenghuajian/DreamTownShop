package controller;

import domain.Orderinfo;
import domain.Orders;
import domain.carinfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.beanutils.BeanUtils;
import service.IOrderService;
import service.IProductService;
import service.impl.OrderServiceImpl;
import service.impl.ProductServiceImpl;
import controller.base.BaseServlet;
import util.PageModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/order")
public class OrderController extends BaseServlet {
	private static final long serialVersionUID = 1L;


	public void viewOrder(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String customerId=(String) session.getAttribute("customerId");
		System.out.println(customerId);

		IProductService productService=new ProductServiceImpl();
		List<carinfo> order=productService.getOrder(customerId);
		System.out.println("carinfo:"+JSON.toJSONString(order));
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(JSON.toJSONString(order));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getOrder(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String customerId=(String) session.getAttribute("customerId");

		String data[]=request.getParameterValues("order");
		session = request.getSession();
		session.setAttribute("order", data);

		/*for(int i=0;i<data.length;i++) {
			System.out.println(data[i]);
		}*/

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(JSON.toJSONString(data));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listOrder(HttpServletRequest request, HttpServletResponse response) {
		String customerId=request.getParameter("customerId");
		HttpSession session = request.getSession();
		Object data=session.getAttribute("order");
		/*for(int i=0;i<data.length;i++) {
			System.out.println("对象"+i+":"+data[i]);
		}*/
		Gson gson=new Gson();
		String order=gson.toJson(data).replace("\\", "");
		order=order.substring(2,order.lastIndexOf("]")-1);
		//System.out.println("订单详情："+order);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(order);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listAllOrder(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out=null;
		try {
			String currentPage=request.getParameter("currentPage");
			if (currentPage == null) {
				currentPage = "1";
			}
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd hh:mm:ss")
					.create();
			IOrderService orderService = new OrderServiceImpl();
			PageModel<Orders> pm = orderService.getAllProduct(Integer.parseInt(currentPage));
			System.out.println(gson.toJson(pm));
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

//确定结算后提交订单并更新购物车
	public void placeOrder(HttpServletRequest request, HttpServletResponse response) {
		//拿到订单商品信息
		HttpSession session = request.getSession();
		Object data=session.getAttribute("order");
		Gson gson=new Gson();
		String order=gson.toJson(data).replace("\\", "");
		order=order.substring(2,order.lastIndexOf("]")-1);

		//System.out.println(order);
		/*orderInfo.ordersId=uuid().replace(/-/g,'');*/
		Orders orders=new Orders();
		//拿到订单的下单状态和买家信息
		String order1=request.getParameter("order");
		try {
			order1 = new String(order1.getBytes("ISO-8859-1"), "UTF-8");
			order = new String(order.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//System.out.println("订单详情："+order);
		System.out.println("提交来的order:"+order1);

		JsonParser jp = new JsonParser();
		JsonObject jo = jp.parse(request.getParameter("order")).getAsJsonObject();

		String street=jo.get("street").getAsString();
		String bname=jo.get("buyerInfo").getAsString();
		try {
			street= new String(street.getBytes("ISO-8859-1"), "UTF-8");
			bname=new String(bname.getBytes("ISO-8859-1"), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		street=street.substring(0, street.length() - 1);
		String temp[]=street.split("\\(");
	//	System.out.println("地址:"+temp[0]);
//		System.out.println("邮编:"+temp[1]);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(sdf.format(new Date()));
			System.out.println(sdf.format(new Date()));
			System.out.println("time:"+date.getTime());
			orders.setOrderDate(new Timestamp(date.getTime()));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<carinfo> carinfolist= JSONObject.parseArray(order,carinfo.class);
		IOrderService orderService=new OrderServiceImpl();
		for (carinfo carinfo : carinfolist) {
			if(carinfo.getName()!=null) {
				Orderinfo orderinfo = new Orderinfo();
				orderinfo.setBaddr(temp[0]);
				orderinfo.setBname(bname);
				orderinfo.setBphone(jo.get("phone").getAsString());
				orderinfo.setCustomerid(jo.get("customerId").getAsString());
				orderinfo.setNum(carinfo.getNum());
				orderinfo.setOrderdate(new Timestamp(date.getTime()));
				orderinfo.setOrderid(UUID.randomUUID().toString().replace("-", ""));
				orderinfo.setExpress("已付款");
				orderinfo.setStatus("已支付,尚未发货");
				orderinfo.setPic(carinfo.getPicURL());
				orderinfo.setPinfo(carinfo.getDescInfo());
				orderinfo.setPname(carinfo.getName());
				orderinfo.setPrice(carinfo.getPrice());
				orderinfo.setProductid(carinfo.getProductId());
				//System.out.println("orderinfo:" + orderinfo);


				//保存订单
				orderService.saveOrder(orderinfo);
				//修改购物车
				orderService.updataCar(orderinfo);
			}

		}

		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write("OK");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addOrder(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = null;
			Map<String, String[]> map = request.getParameterMap();
			Orders order = new Orders();
			BeanUtils.populate(order, map);

			IOrderService orderService = new OrderServiceImpl();
			orderService.update(order);
			out = response.getWriter();
			out.write("OK");
			out.flush();
			out.close();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		String productId=request.getParameter("productId");
		IOrderService orderService=new OrderServiceImpl();
		orderService.delete(productId);
	}
	public void updateOrderinfo(HttpServletRequest request, HttpServletResponse response) {
		String orderid=request.getParameter("orderid");
		System.out.println("orderid:"+orderid);
		String status=request.getParameter("status");
		System.out.println("status:"+status);
		try {
			PrintWriter out = null;

			IOrderService orderService = new OrderServiceImpl();
			orderService.updateOrderinfo(orderid,status);
			out = response.getWriter();
			out.write("OK");
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void deleteOrderinfo(HttpServletRequest request, HttpServletResponse response) {
		String orderid=request.getParameter("id");
		System.out.println("orderid:"+orderid);
		String status=request.getParameter("status");
		System.out.println("status:"+status);
		try {
			PrintWriter out = null;

			IOrderService orderService = new OrderServiceImpl();
			orderService.deleteOrderinfo(orderid,status);
			out = response.getWriter();
			//out.write("OK");
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cancelOrderinfo(HttpServletRequest request, HttpServletResponse response) {
		String orderid=request.getParameter("id");
		System.out.println("orderid:"+orderid);

		try {
			PrintWriter out = null;

			IOrderService orderService = new OrderServiceImpl();
			orderService.cancelOrder(orderid);
			out = response.getWriter();
			out.write("OK");
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//用户获取订单信息
	public void getOrderinfo(HttpServletRequest request, HttpServletResponse response) {
		String customerid= request.getParameter("customerid");
		System.out.println("customerid:"+customerid);
		PrintWriter out=null;

		try {
			String currentPage=request.getParameter("currentPage");

			System.out.println(currentPage);
			//System.out.println(productId);
			if (currentPage == null) {
				currentPage = "1";
			}
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd")
					.create();
			IOrderService orderService = new OrderServiceImpl();
			PageModel<Orderinfo> pm = orderService.getOderinfo(Integer.parseInt(currentPage),customerid);
			pm.setCurrentPage(Integer.parseInt(currentPage));

			response.setContentType("application/json;charset=UTF-8");
			//System.out.println("pm:"+pm);
			System.out.println("用户看到的订单："+gson.toJson(pm));

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
	public void listOrderinfo(HttpServletRequest request, HttpServletResponse response) {
		String shopid=(String) request.getSession().getAttribute("shopid");
		System.out.println("shopid:"+shopid);
		PrintWriter out=null;

		try {
			String currentPage=request.getParameter("currentPage");

			System.out.println(currentPage);
			//System.out.println(productId);
			if (currentPage == null) {
				currentPage = "1";
			}
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd")
					.create();
			IOrderService orderService = new OrderServiceImpl();
			PageModel<Orderinfo> pm = orderService.listOrderinfo(Integer.parseInt(currentPage),shopid);
			pm.setCurrentPage(Integer.parseInt(currentPage));

			response.setContentType("application/json;charset=UTF-8");
			//System.out.println("pm:"+pm);
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
}
