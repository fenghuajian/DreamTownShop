package bean;

public class Shop {

    private String customerid;
    private String shopid;
    private String shopname;

    @Override
    public String toString() {
        return "Shop{" +
                "customerid='" + customerid + '\'' +
                ", shopid='" + shopid + '\'' +
                ", shopname='" + shopname + '\'' +
                '}';
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

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
}
