package servlet;

import bean.Orders;
import bean.carinfo;
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
import servlet.base.BaseServlet;
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
//确定结算后
	public void placeOrder(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Object data=session.getAttribute("order");
		Gson gson=new Gson();
		String order=gson.toJson(data).replace("\\", "");
		order=order.substring(2,order.lastIndexOf("]")-1);
		//System.out.println(order);
		/*orderInfo.ordersId=uuid().replace(/-/g,'');*/
		Orders orders=new Orders();
		String order1=request.getParameter("order");
		try {
			order1 = new String(order1.getBytes("ISO-8859-1"), "UTF-8");
			order = new String(order.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<carinfo> carinfolist= JSONObject.parseArray(order,carinfo.class);
		    for (carinfo carinfo : carinfolist) {
			        System.out.println(carinfo.getName());
			   }

		//System.out.println("订单详情："+order);
		System.out.println("提交来的order:"+order1);

		JsonParser jp = new JsonParser();
		JsonObject jo = jp.parse(request.getParameter("order")).getAsJsonObject();

		String street=jo.get("street").getAsString();
		try {
			street= new String(street.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		street=street.substring(0, street.length() - 1);
		String temp[]=street.split("\\(");

		System.out.println("地址:"+temp[0]);
		System.out.println("邮编:"+temp[1]);
		orders.setOrdersId(jo.get("ordersId").getAsString());
		orders.setCustomerId(jo.get("customerId").getAsString());
		orders.setAmount(Float.parseFloat(jo.get("amount").getAsString()));
		//orders.setStatus(jo.get("status").getAsString());
		orders.setBuyerInfo(jo.get("buyerInfo").getAsString());

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = sdf.parse(sdf.format(new Date()));
			System.out.println(sdf.format(new Date()));
			System.out.println(date.getTime());
			orders.setOrderDate(new Timestamp(date.getTime()));

		} catch (ParseException e) {
			e.printStackTrace();
		}

	//	orders.setCashInfo(jo.get("cashInfo").getAsString());
	//	orders.setExpressInfo(jo.get("expressInfo").getAsString());

		IOrderService orderService=new OrderServiceImpl();
		//orderService.saveOrder(orders);
		System.out.println("orders:"+orders);
		System.out.println(orders.getOrderDate());

		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			//out.write("OK");
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
}
