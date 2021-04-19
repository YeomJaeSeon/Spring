package study2.fighting.order;

import study2.fighting.domain.Grade;
import study2.fighting.domain.Member;

public class RateDiscountPolicy implements DiscountPolicy{
    private final int discountRate = 10;


    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountRate / 100;
        }
        return 0;
    }
}
