package restudy.restudy2.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import restudy.restudy2.member.Grade;
import restudy.restudy2.member.Member;
import restudy.restudy2.member.MemberRepository;
import restudy.restudy2.member.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    OrderService orderService = new OrderServiceImpl();
    MemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    void createOrder(){

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberRepository.save(member);

        Order orderedItemA = orderService.createOrder(member.getId(), "itemA", 10000);

        assertThat(orderedItemA.getDiscount()).isEqualTo(1000);
        assertThat(orderedItemA.getDiscountedPrice()).isEqualTo(9000);
    }

}