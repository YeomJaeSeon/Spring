package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class Test {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "MemberA", Grade.VIP);
        // 저장함.
        memberService.join(member);

        Order orderA = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("orderA.calculatePrice() = " + orderA.calculatePrice());
        System.out.println("orderA.getDisCountPrice() = " + orderA.getDisCountPrice());

    }
}
