package restudy.gogogo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import restudy.gogogo.domain.Grade;
import restudy.gogogo.domain.Member;
import restudy.gogogo.domain.Order;
import restudy.gogogo.service.MemberService;
import restudy.gogogo.service.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = ac.getBean("orderService",OrderService.class);
        MemberService memberService = ac.getBean("memberService" ,MemberService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        System.out.println("order = " + order);
        System.out.println(order.calculatePrice());
    }
}
