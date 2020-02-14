package bean;

public class tour {

    private String aname;
    private  String brief;
    private int aid;
    private  String details;
    private String linked;
    private  String ad;
    private  String area;
    private String picurl;
    private  String spicurl;

    @Override
    public String toString() {
        return "tour{" +
                "aname='" + aname + '\'' +
                ", brief='" + brief + '\'' +
                ", aid=" + aid +
                ", details='" + details + '\'' +
                ", linked='" + linked + '\'' +
                ", ad='" + ad + '\'' +
                ", area='" + area + '\'' +
                ", picurl='" + picurl + '\'' +
                ", spicurl='" + spicurl + '\'' +
                '}';
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLinked() {
        return linked;
    }

    public void setLinked(String linked) {
        this.linked = linked;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getSpicurl() {
        return spicurl;
    }

    public void setSpicurl(String spicurl) {
        this.spicurl = spicurl;
    }
}
