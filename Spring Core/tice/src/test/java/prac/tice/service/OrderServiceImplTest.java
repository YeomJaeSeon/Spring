package prac.tice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prac.tice.AppConfig;
import prac.tice.domain.Grade;
import prac.tice.domain.Member;
import prac.tice.domain.Order;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {
    AppConfig appConfig = new AppConfig();
    OrderService orderService;
    MemberService memberService;

    @BeforeEach
    void beforeEach(){
        orderService = appConfig.orderService();
        memberService = appConfig.memberService();
    }

    @Test
    @DisplayName("vip")
    void createOrder(){
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(1L, "item", 20000);

        assertThat(order.calculatePrice()).isEqualTo(19000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}