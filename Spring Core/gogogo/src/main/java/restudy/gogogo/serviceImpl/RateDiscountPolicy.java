package restudy.gogogo.serviceImpl;

import org.springframework.stereotype.Component;
import restudy.gogogo.domain.Grade;
import restudy.gogogo.domain.Member;
import restudy.gogogo.service.DiscountPolicy;


@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private final int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }
        return 0;
    }
}
