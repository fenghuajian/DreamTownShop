package bean;

import java.sql.Timestamp;

public class Orders {
	private String ordersId;//订单id

	private String customerId;//用户id
	private Float amount;//订单数量
	private String status;//订单状态
	private String buyerInfo;//买家信息
	private Timestamp orderDate;//订单日期
	private String cashInfo;//信息
	private String expressInfo;//交易信息
	
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
