package food.studentsBestt.item.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
    private String itemName;
    private Long id;
    private Integer price;
    private Integer amount;

    public Item(String itemName, Integer price, Integer amount) {
        this.itemName = itemName;
        this.price = price;
        this.amount = amount;
    }
    public Item(){

    }
}
