package restudy.gogogo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import restudy.gogogo.repository.MemberRepository;
import restudy.gogogo.repositoryimpl.MemoryMemberRepository;
import restudy.gogogo.service.DiscountPolicy;
import restudy.gogogo.service.MemberService;
import restudy.gogogo.service.OrderService;
import restudy.gogogo.serviceImpl.MemberServiceImpl;
import restudy.gogogo.serviceImpl.OrderServiceImpl;
import restudy.gogogo.serviceImpl.RateDiscountPolicy;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}

// 이젠 설정파일답게 한눈에 다 들어온다. - 역할과 구현으로 분리해서(DiscountPolicy라던가, MemberRepository라던가..)