package restudy.restudy2.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restudy.restudy2.AppConfig;
import restudy.restudy2.member.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Order orderedItemA = orderService.createOrder(member.getId(), "itemA", 10000);

        assertThat(orderedItemA.getDiscount()).isEqualTo(1000);
        assertThat(orderedItemA.getDiscountedPrice()).isEqualTo(9000);
    }

}