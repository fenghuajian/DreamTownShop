package bean;

public class Category {
    private String categoryid;
    private String name;

    public String getCategoryid() {
        return categoryid;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryid='" + categoryid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
