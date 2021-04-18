package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Config ~ 어플리케이션의 설정정보를 의미함.
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository()); // 스프링빈
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); // 실제 메서드가 호출한 결과인 이 객체가
        // 스프링 빈의 value로 등록되어있다. 즉, 스프링 빈으로 등록되어있다. 이 객체들이
    }

    // 메서드 이름은 스프링 빈의 key로 등록되어져있음 단순히 스프링 value에 접근하기 위한 이름일뿐.
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy()); //스프링 빈
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    // Spring 컨테이너라는곳에 다 등록이됨 @Bean하면.
}
