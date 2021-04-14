package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Order itemA = orderService.createOrder(1L, "itemA", 10000);

//        Assertions.assertThat(/)
        Assertions.assertThat(itemA.getDisCountPrice()).isEqualTo(1000);

    }
}
