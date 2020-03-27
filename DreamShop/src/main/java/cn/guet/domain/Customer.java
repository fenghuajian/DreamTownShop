package cn.guet.domain;

public class Customer {

	private String customerid;
	private String username;
	private String password;
	private String phone;
	private String mailbox;
	private String defaultname;
	private String defaultphone;
	private String defaultaddr;

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getDefaultname() {
		return defaultname;
	}

	public void setDefaultname(String defaultname) {
		this.defaultname = defaultname;
	}

	public String getDefaultphone() {
		return defaultphone;
	}

	public void setDefaultphone(String defaultphone) {
		this.defaultphone = defaultphone;
	}

	public String getDefaultaddr() {
		return defaultaddr;
	}

	public void setDefaultaddr(String defaultaddr) {
		this.defaultaddr = defaultaddr;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerid='" + customerid + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", phone='" + phone + '\'' +
				", mailbox='" + mailbox + '\'' +
				", defaultname='" + defaultname + '\'' +
				", defaultphone='" + defaultphone + '\'' +
				", defaultaddr='" + defaultaddr + '\'' +
				'}';
	}
}