package esd.scos.model;

// 食品实体类
public class Food {

    // 菜名
    private String foodName;
    // 价格
    private int price;
    // 库存;
    private int store;
    // 是否点单
    private boolean order;
    // 图片资源ID;
    private int imgId;

    public Food(String foodName, int price, int store, boolean order, int imgId) {
        this.foodName = foodName;
        this.price = price;
        this.store = store;
        this.order = order;
        this.imgId = imgId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

}
