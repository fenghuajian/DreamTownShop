package servlet;

import bean.Customer;
import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.ICustomerService;
import service.impl.CustomerServiceImpl;
import servlet.base.BaseServlet;
import util.SendEmail;
import util.UUIDString;

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
//�û���¼
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
//��ȡ�û���Ϣ
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

		ICustomerService customerService=new CustomerServiceImpl();


		if(customerid !=null &&shopid !=null) {
			customerService.addShop(customerid,shopid,shopname);
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

	public void sendVerifyCode(HttpServletRequest request, HttpServletResponse response){
		PrintWriter out;
		String  phone=request.getParameter("phone");
		String code="123456";
		System.out.println(phone);
		if(!code.equals("")) {
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
	}

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

	public void checkCode(HttpServletRequest request, HttpServletResponse response){

		String code=request.getParameter("code");
		System.out.println(code);
		if(request.getSession().getAttribute("code").equals(code)) {
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

	public void saveCustomer(HttpServletRequest request, HttpServletResponse response) {
		Customer customer=new Customer();
		customer.setCustomerId(UUIDString.getId());
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
			customerService.save(customer);
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