package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; //1000원 정액할인.
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){ // ENUM은 ==써도됨!
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
