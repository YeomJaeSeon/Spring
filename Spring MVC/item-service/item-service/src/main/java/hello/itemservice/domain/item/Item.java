package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

//@Data 위험함. 너무 많은것들이 존재함.
@Getter @Setter
public class Item {
    private Long id;
    private String itemName;
    private Integer price; // ref타입 변수로한이유는 null이 들어갈수도있으므로. int면
    //0이라도 들어가야함.
    private Integer quantity;

    public Item(){

    }
    public Item(String itemName, Integer price, Integer quantity){
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
