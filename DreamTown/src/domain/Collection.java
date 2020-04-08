package domain;

public class Collection  {

    private  String productid;
    private  String customerid;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "productid='" + productid + '\'' +
                ", customerid='" + customerid + '\'' +
                '}';
    }
}
