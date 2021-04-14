package hello.core.order;

public class Order {
    private Long id;
    private String itemName;
    private int itemPrice;
    private int disCountPrice; // 할인가격

    public Order(Long id, String itemName, int itemPrice, int disCountPrice) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.disCountPrice = disCountPrice;
    }

    public int calculatePrice(){
        return itemPrice - disCountPrice;
    }

    public int getDisCountPrice() {
        return disCountPrice;
    }

    public void setDisCountPrice(int disCountPrice) {
        this.disCountPrice = disCountPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
