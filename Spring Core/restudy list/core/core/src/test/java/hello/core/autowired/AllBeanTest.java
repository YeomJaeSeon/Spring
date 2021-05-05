package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    
    @Test
    void findAllBeans(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        // spring 컨테이너 생성하면서 스프링빈등록.
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        FixDiscountPolicy fixDiscountPolicy = ac.getBean(FixDiscountPolicy.class);

        int discountPrice = discountService.discount(member, 20000, fixDiscountPolicy);

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        RateDiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);




    }
    
    static class DiscountService{
        private final Map<String, DiscountPolicy> discountPolicyMap;
        private final List<DiscountPolicy> discountPolicyList;
        // Map, List

        @Autowired // 생성자하나이면 자동으로 의존관계주입해주는 @Autowired생략된다. Map, List로 파라미텁다으면 해당타입의 빈들을 모두 받는다. - 스프링의 특수기능임.
        public DiscountService(Map<String, DiscountPolicy> discountPolicyMap, List<DiscountPolicy> discountPolicyList) {
            this.discountPolicyMap = discountPolicyMap;
            this.discountPolicyList = discountPolicyList;
            System.out.println("discountPolicyMap = " + discountPolicyMap);
            System.out.println("discountPolicyList = " + discountPolicyList);
        }

        public int discount(Member member, int price, DiscountPolicy discountCode) {
            int idx = discountPolicyList.indexOf(discountCode);
            DiscountPolicy discountPolicy = discountPolicyList.get(idx);
            int discount = discountPolicy.discount(member, price);

            return discount;
        }
    }
}
