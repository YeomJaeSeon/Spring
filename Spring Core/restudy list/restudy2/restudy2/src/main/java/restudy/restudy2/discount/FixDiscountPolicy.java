package restudy.restudy2.discount;

import restudy.restudy2.member.Grade;

public class FixDiscountPolicy implements DiscountPolicy{
    private final int FIX_DISCOUNT_AMOUNT = 1000;
    @Override
    public int discount(Grade grade, int itemPrice) {
        if(grade == Grade.VIP){
            return FIX_DISCOUNT_AMOUNT;
        }else{
            return 0;
        }
    }
}
