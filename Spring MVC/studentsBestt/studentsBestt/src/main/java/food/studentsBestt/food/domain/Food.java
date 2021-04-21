package food.studentsBestt.food.domain;

import lombok.Getter;
import lombok.Setter;

public class Food {
    private Long id;
    private String foodName;
    private int likesNum;
    private int hatesNum;


    public Food(String foodName) {
        this.foodName = foodName;
        likesNum = 0;
        hatesNum = 0;
    }
    public Food(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(int likesNum) {
        this.likesNum = likesNum;
    }

    public int getHatesNum() {
        return hatesNum;
    }

    public void setHatesNum(int hatesNum) {
        this.hatesNum = hatesNum;
    }

}
