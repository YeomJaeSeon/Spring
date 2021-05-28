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

public class AppConfig2 {
    private static final MemberRepository memberRepositoryInstance = new MemoryMemberRepository();
    private static final DiscountPolicy discountPolicyInstance = new RateDiscountPolicy();
    private static final MemberService memberInstance = new MemberServiceImpl(memberRepositoryInstance);
    private static final OrderService orderInstance = new OrderServiceImpl(memberRepositoryInstance, discountPolicyInstance);
    // 인스턴스 레벨의 멤버는 스태틱 레벨의 멤버 사용가능..
    // 반대는안됨 스태틹 레벨에서 인스턴스 레벨의 멤버는 사용불가. - 메모리에 클래스가 생성될때 static은 생성, 인스턴스는 객체생성될떄 iv, 등 생성되므로

    public static MemberService memberService(){
        return memberInstance;
    }

    public static OrderService orderService(){
        return orderInstance;
    }

    public static MemberRepository memberRepository(){
        return memberRepositoryInstance;
    }

    public static DiscountPolicy discountPolicy() {
        return discountPolicyInstance;
    }

}

// 이젠 설정파일답게 한눈에 다 들어온다. - 역할과 구현으로 분리해서(DiscountPolicy라던가, MemberRepository라던가..)