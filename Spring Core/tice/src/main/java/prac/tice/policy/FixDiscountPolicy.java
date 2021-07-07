package prac.tice.policy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import prac.tice.domain.Grade;
import prac.tice.domain.Member;

@Component
@Qualifier("mainDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{
    private final int discountPrice = 1000;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return 1000;
        }
        return 0;
    }
}
