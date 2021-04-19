package study2.fighting.order;

import study2.fighting.domain.Grade;
import study2.fighting.domain.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    private final int discountPrice = 1000;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountPrice;
        }
        return 0;
    }
}
