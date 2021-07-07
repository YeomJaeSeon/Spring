package prac.tice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import prac.tice.policy.DiscountPolicy;
import prac.tice.policy.FixDiscountPolicy;
import prac.tice.policy.RateDiscountPolicy;
import prac.tice.repository.MemberRepository;
import prac.tice.repository.MemoryMemberRepository;
import prac.tice.service.MemberService;
import prac.tice.service.MemberServiceImpl;
import prac.tice.service.OrderService;
import prac.tice.service.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
