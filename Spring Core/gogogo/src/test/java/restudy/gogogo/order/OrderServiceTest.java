package restudy.gogogo.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restudy.gogogo.AppConfig;
import restudy.gogogo.domain.Grade;
import restudy.gogogo.domain.Member;
import restudy.gogogo.domain.Order;
import restudy.gogogo.repository.MemberRepository;
import restudy.gogogo.repositoryimpl.MemoryMemberRepository;
import restudy.gogogo.service.MemberService;
import restudy.gogogo.service.OrderService;
import restudy.gogogo.serviceImpl.MemberServiceImpl;
import restudy.gogogo.serviceImpl.OrderServiceImpl;
import restudy.gogogo.serviceImpl.RateDiscountPolicy;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    @DisplayName("order test")
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        assertThat(order.getDiscountPrice()).isEqualTo(2000);
    }

    // field injection하면 스프링없이 순수자바로 단위테스트가 어렵다
    @Test
    void fieldInjectionTest(){
//        Member member = new Member(1L, "memberA", Grade.VIP);
//
//        MemberRepository memberRepository = new MemoryMemberRepository();
//        memberRepository.save(member);
//
//        OrderServiceImpl orderService = new OrderServiceImpl(); // 순수한 자바로 단위테스트
//        orderService.setDiscountPolicy(new RateDiscountPolicy());
//        orderService.setMemberRepository(memberRepository);
//
//
//        Order itemA = orderService.createOrder(1L, "itemA", 10000);
//
//        Assertions.assertThat(itemA.getDiscountPrice()).isEqualTo(1000);
    }
}
