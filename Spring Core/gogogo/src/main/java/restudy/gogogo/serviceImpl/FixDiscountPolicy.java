package restudy.gogogo.serviceImpl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import restudy.gogogo.domain.Grade;
import restudy.gogogo.domain.Member;
import restudy.gogogo.service.DiscountPolicy;

@Component
public class FixDiscountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
