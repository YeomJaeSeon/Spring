package prac.tice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import prac.tice.domain.Grade;
import prac.tice.domain.Member;
import prac.tice.domain.Order;
import prac.tice.service.MemberService;
import prac.tice.service.OrderService;

public class OrderApp {
    public static void main(String[] args) {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = ac.getBean(OrderService.class);
        MemberService memberService = ac.getBean(MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "item", 20000);

        System.out.println(order.calculatePrice());
        System.out.println(order.getDiscountPrice());


    }
}
