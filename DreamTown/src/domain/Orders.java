package domain;

import java.sql.Timestamp;

public class Orders {
	private String ordersId;//����id

	private String customerId;//�û�id
	private Float amount;//��������
	private String status;//����״̬
	private String buyerInfo;//�����Ϣ
	private Timestamp orderDate;//��������
	private String cashInfo;//��Ϣ
	private String expressInfo;//������Ϣ
	
	public String getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBuyerInfo() {
		return buyerInfo;
	}
	public void setBuyerInfo(String buyerInfo) {
		this.buyerInfo = buyerInfo;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public String getCashInfo() {
		return cashInfo;
	}
	public void setCashInfo(String cashInfo) {
		this.cashInfo = cashInfo;
	}
	public String getExpressInfo() {
		return expressInfo;
	}
	public void setExpressInfo(String expressInfo) {
		this.expressInfo = expressInfo;
	}
}
