package restudy.restudy2;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import restudy.restudy2.discount.DiscountPolicy;
import restudy.restudy2.discount.FixDiscountPolicy;
import restudy.restudy2.discount.RateDiscountPolicy;
import restudy.restudy2.member.MemberRepository;
import restudy.restudy2.member.MemberService;
import restudy.restudy2.member.MemberServiceImpl;
import restudy.restudy2.member.MemoryMemberRepository;
import restudy.restudy2.order.OrderService;
import restudy.restudy2.order.OrderServiceImpl;

// 실제 구현체를 만들어주고 의존관계 설정해줌.

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 완전쉽게 구현체 변경ㄴ
    }
}
