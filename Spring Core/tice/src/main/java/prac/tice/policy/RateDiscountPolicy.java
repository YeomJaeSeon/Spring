package prac.tice.policy;

import org.springframework.stereotype.Component;
import prac.tice.domain.Grade;
import prac.tice.domain.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountRate = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price / 100 * 10;
        }
        return 0;
    }
}
