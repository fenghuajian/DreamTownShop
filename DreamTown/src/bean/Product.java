package bean;

import java.sql.Date;

public class Product {

	private String productId;
	private String categoryId;
	private String name;
	//��װ����
	private Float price;
	private Date onlineDate;
	private String descInfo;
	private String picURL;
	private String isJingXuan;
	private String isReMai;
	private String isXiaJia;
	private String shopid;
	private String shopname;

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productId='" + productId + '\'' +
				", categoryId='" + categoryId + '\'' +
				", name='" + name + '\'' +
				", price=" + price +
				", onlineDate=" + onlineDate +
				", descInfo='" + descInfo + '\'' +
				", picURL='" + picURL + '\'' +
				", isJingXuan='" + isJingXuan + '\'' +
				", isReMai='" + isReMai + '\'' +
				", isXiaJia='" + isXiaJia + '\'' +
				", shopid='" + shopid + '\'' +
				", shopname='" + shopname + '\'' +
				'}';
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Date getOnlineDate() {
		return onlineDate;
	}
	public void setOnlineDate(Date onlineDate) {
		this.onlineDate = onlineDate;
	}
	public String getDescInfo() {
		return descInfo;
	}
	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
	}
	public String getPicURL() {
		return picURL;
	}
	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}
	public String getIsJingXuan() {
		return isJingXuan;
	}
	public void setIsJingXuan(String isJingXuan) {
		this.isJingXuan = isJingXuan;
	}
	public String getIsReMai() {
		return isReMai;
	}
	public void setIsReMai(String isReMai) {
		this.isReMai = isReMai;
	}
	public String getIsXiaJia() {
		return isXiaJia;
	}
	public void setIsXiaJia(String isXiaJia) {
		this.isXiaJia = isXiaJia;
	}
}