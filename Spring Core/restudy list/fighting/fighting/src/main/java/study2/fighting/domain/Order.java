package study2.fighting.domain;

public class Order {
    private Long memberId;
    private String itemName;
    private Integer price;
    private Integer discount;

    public Integer getItemSaleResult(){
        return price - discount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Order(Long memberId, String itemName, Integer price, Integer discount) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.price = price;
        this.discount =discount;
    }
}
