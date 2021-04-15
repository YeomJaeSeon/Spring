package restudy.restudy2;


import restudy.restudy2.discount.FixDiscountPolicy;
import restudy.restudy2.member.MemberRepository;
import restudy.restudy2.member.MemberService;
import restudy.restudy2.member.MemberServiceImpl;
import restudy.restudy2.member.MemoryMemberRepository;
import restudy.restudy2.order.OrderService;
import restudy.restudy2.order.OrderServiceImpl;

// 실제 구현체를 만들어주고 의존관계 설정해줌.
public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
