package restudy.restudy2.discount;

import restudy.restudy2.member.Grade;

public class RateDiscountPolicy implements DiscountPolicy{
    private int rate = 10;

    @Override
    public int discount(Grade grade, int itemPrice) {
        if(grade == Grade.VIP){
            return itemPrice * rate / 100;
        }
        return 0;
    }
}
