package cn.guet.domain;

import java.sql.Timestamp;

public class Orderinfo {
    private  String orderid;
    private  String status;
    private  String express;
    private Timestamp orderdate;
    private String productid;
    private String pname;
    private  Float price;
    private  String pic;
    private int num;
    private String pinfo;
    private  String customerid;
    private String bname;
    private  String bphone;
    private  String baddr;

    public Orderinfo() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return "Orderinfo{" +
                "orderid='" + orderid + '\'' +
                ", status='" + status + '\'' +
                ", express='" + express + '\'' +
                ", orderdate=" + orderdate +
                ", productid='" + productid + '\'' +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                ", num=" + num +
                ", pinfo='" + pinfo + '\'' +
                ", customerid='" + customerid + '\'' +
                ", bname='" + bname + '\'' +
                ", bphone='" + bphone + '\'' +
                ", baddr='" + baddr + '\'' +
                '}';
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public Timestamp getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Timestamp orderdate) {
        this.orderdate = orderdate;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPinfo() {
        return pinfo;
    }

    public void setPinfo(String pinfo) {
        this.pinfo = pinfo;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBphone() {
        return bphone;
    }

    public void setBphone(String bphone) {
        this.bphone = bphone;
    }

    public String getBaddr() {
        return baddr;
    }

    public void setBaddr(String baddr) {
        this.baddr = baddr;
    }
}
