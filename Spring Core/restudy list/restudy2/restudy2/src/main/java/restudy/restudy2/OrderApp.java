package restudy.restudy2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import restudy.restudy2.member.Grade;
import restudy.restudy2.member.Member;
import restudy.restudy2.member.MemberService;
import restudy.restudy2.order.Order;
import restudy.restudy2.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        // applicationContext : 스프링빈이 등록되어져있는 스프링컨테이너
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);
        Order itemA = orderService.createOrder(1L, "itemA", 20000);
        int discount = itemA.getDiscount();
        System.out.println(discount);

    }
}
