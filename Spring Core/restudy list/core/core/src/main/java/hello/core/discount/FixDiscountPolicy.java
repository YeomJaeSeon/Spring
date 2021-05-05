package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy // 컴파일 에러로 오류를 잡을수있는 장점이있다.!!
public class FixDiscountPolicy implements DiscountPolicy{
    private int disCountPrice = 1000;

    @Override
    public int discount(Member member, int itemPrice) {
        if(member.getGrade() == Grade.VIP){
            return disCountPrice;
        }else{
            return 0;
        }
    }
}
