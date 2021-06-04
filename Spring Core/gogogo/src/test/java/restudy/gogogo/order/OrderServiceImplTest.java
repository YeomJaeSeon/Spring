package restudy.gogogo.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import restudy.gogogo.domain.Grade;
import restudy.gogogo.domain.Member;
import restudy.gogogo.domain.Order;
import restudy.gogogo.repositoryimpl.MemoryMemberRepository;
import restudy.gogogo.serviceImpl.FixDiscountPolicy;
import restudy.gogogo.serviceImpl.OrderServiceImpl;
import restudy.gogogo.serviceImpl.RateDiscountPolicy;

public class OrderServiceImplTest {

//    @Test
//    void createOrder(){
//        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
//        memberRepository.save(new Member(1L, "memberA", Grade.VIP));
//
//        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//        Order order = orderService.createOrder(1L, "itemA", 20000);
//        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
//    }

    @Test
    void reTestCreateOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "userA", Grade.VIP));


        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new RateDiscountPolicy()); // 생성자 주입을 이용하면 누락을 막아줌 - 객체 생성될때 생성자가 호출되기떄문에 생성자주입을통해서
        // 의존관계 누락을 막아줌
        Order order = orderService.createOrder(1L, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}

