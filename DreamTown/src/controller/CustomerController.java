package controller;

import com.alibaba.fastjson.JSON;
import controller.base.BaseServlet;
import domain.Customer;
import service.ICustomerService;
import service.impl.CustomerServiceImpl;
import util.SendEmail;
import util.UUIDString;
import util.getCode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@WebServlet("/customer")
public class CustomerController extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public void login(HttpServletRequest request, HttpServletResponse response) {
		String customerName=request.getParameter("username");
		String password=request.getParameter("password");

		System.out.println(customerName+","+password);

		ICustomerService customerService=new CustomerServiceImpl();
		String result=customerService.verify(customerName, password);
		System.out.println("customerId:"+result);

		if(!"".equals(result) && result!=null) {
			String customerId=result;
			HttpSession session=request.getSession();
			session.setAttribute("customerId", customerId);
			session.setAttribute("customername",customerName);
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.print(customerName);
			out.flush();
			out.close();
		}

	}
	/**
	 * 给网页返回用户信息
	 * @param request
	 * @param response
	 */
	/**
	 * 获取用户信息
	 * @param request
	 * @param response
	 */
	public void getInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String customerId=(String) session.getAttribute("customerId");
		//System.out.println(customerId);
		ICustomerService customerService=new CustomerServiceImpl();
		Customer customer=customerService.getById(customerId);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			System.out.println(JSON.toJSONString(customer));
			out.write(JSON.toJSONString(customer));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addShop(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String customerid=request.getParameter("id");
		System.out.println("id:"+customerid);
		String shopname=request.getParameter("name");
		shopname = new String(shopname.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("name:"+shopname);
		String shopid=UUIDString.getId();
		System.out.println("shopid:"+shopid);
       String userid=customerid;
       String roleid="d26a3b7228d14a518df62e30f9fb2df1";
		ICustomerService customerService=new CustomerServiceImpl();
		if(customerid !=null &&shopid !=null) {
			customerService.addShop(customerid,shopid,shopname);
            customerService.updaterole(userid,roleid);
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

	}

	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	public void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String cid=request.getParameter("cid");
		System.out.println("cid:"+cid);

		String cname=request.getParameter("cname");
		System.out.println("cname:"+cname);

		String dname=request.getParameter("dname");
		dname = new String(dname.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("dname:"+dname);

		String pwd=request.getParameter("pwd");
		System.out.println("pwd:"+pwd);

		String phone=request.getParameter("phone");
		System.out.println("phone:"+phone);

		String mail=request.getParameter("mail");
		System.out.println("phone:"+phone);

		String dphone=request.getParameter("dphone");
		System.out.println("mail:"+mail);

		String addr=request.getParameter("addr");
		addr = new String(addr.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("addr:"+addr);

		Customer customer=new Customer();
		customer.setUsername(cname);
		customer.setPassword(pwd);
		customer.setPhone(phone);
		customer.setDefaultName(dname);
		customer.setCustomerId(cid);
		customer.setMailBox(mail);
		customer.setDefaultPhone(dphone);
		customer.setDefaultAddr(addr);

		ICustomerService customerService=new CustomerServiceImpl() ;

		if(cid !=null &&pwd !=null) {
			customerService.updateCustomer(customer);
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


	}

	/**
	 * 发送手机验证码
	 * @param request
	 * @param response
	 */
	public void sendPhoneCode(HttpServletRequest request, HttpServletResponse response){
			String phone=request.getParameter("phone");
			System.out.println("手机号为："+phone);
			String code= getCode.vcode();
			System.out.println("验证码为："+code);
			request.getSession().setAttribute("phoneCode", code);
			//SendPhoneCode.sendCode(phone,code);
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
	/**
	 * 手机验证
	 * @param request
	 * @param response
	 */
	public void sendVerifyCode(HttpServletRequest request, HttpServletResponse response){
		PrintWriter out;
		String  phone=request.getParameter("phone");
		String chek=request.getParameter("code");
		String code=(String) request.getSession().getAttribute("phoneCode");
		System.out.println("chek:"+chek);
		System.out.println("code:"+code);
		if(code.equals(chek)) {
			System.out.println("yes");
			//request.getSession().setAttribute("code", code);
			response.setContentType("text/plain;charset=UTF-8");
			try {
				out = response.getWriter();
				out.write("OK");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送邮箱验证码
	 * @param request
	 * @param response
	 */
	public void sendEmailCode(HttpServletRequest request, HttpServletResponse response){
		PrintWriter out;
		String email=request.getParameter("mail");
		String code=UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 5);
		System.out.println("mail:"+email);
		System.out.println("EMAILCODE:"+code);
		//2274105767@qq.com
		SendEmail.recmail(email,code);
		request.getSession().setAttribute("code", code);
		response.setContentType("text/plain;charset=UTF-8");
		try {
			out = response.getWriter();
			out.write("OK");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证邮箱
	 * @param request
	 * @param response
	 */
	public void checkCode(HttpServletRequest request, HttpServletResponse response){

		String code=request.getParameter("code");
		String code1=(String) request.getSession().getAttribute("code");
		System.out.println(code);
		//不区分大小写判断
		if(code.equalsIgnoreCase(code1)) {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out;
			try {
				System.out.println("yes");
				out = response.getWriter();
				//out.write("hhh");
				out.write("OK");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断用户是否存在
	 * @param request
	 * @param response
	 */
	public void checkAccount(HttpServletRequest request, HttpServletResponse response) {
		String account=request.getParameter("account");
		ICustomerService customerService=new CustomerServiceImpl();
		System.out.println(customerService.isExist(account));
		if(customerService.isExist(account)==0) {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out;
			try {
				out=response.getWriter();
				out.write("OK");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 保存用户
	 * @param request
	 * @param response
	 */
	public void saveCustomer(HttpServletRequest request, HttpServletResponse response) {
		Customer customer=new Customer();
	    String customerid=UUIDString.getId();
        String userid=customerid;
		customer.setCustomerId(customerid);
		String username=request.getParameter("username");//
		String password=request.getParameter("password");//
		String mail=request.getParameter("mail");//
		String phone=request.getParameter("phone");
		//String pName=request.getParameter("pname");//
		System.out.println("pname:"+username);
		//System.out.println("pname1:"+pname);
		System.out.println("pwd:"+password);
		System.out.println("mail:"+mail);
		System.out.println("phone:"+phone);
		customer.setUsername(username);
		customer.setMailBox(mail);
		customer.setPhone(phone);
		customer.setPassword(password);
		try {
			/*BeanUtils.populate(customer,request.getParameterMap());*/
			ICustomerService customerService=new CustomerServiceImpl();
			//保存用户信息
			customerService.save(customer);
			//在user也保存一份
			customerService.saveUser(userid,username,password);
			System.out.println(customer);
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out;
			out=response.getWriter();
			out.write("OK");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}