package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        // 스프링 컨테이너생성, 설정정보같이넘기면서 스프링 빈도 등록되고(메서드이름ㅇ로 key, 호출된 결과 객체를 value로..)그리고 설정정보를 통해서
        // 스프링 빈간의 DI로 의존관계가 설정된다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "MemberA", Grade.VIP);
        // 저장함.
        memberService.join(member);

        Order orderA = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("orderA.calculatePrice() = " + orderA.calculatePrice());
        System.out.println("orderA.getDisCountPrice() = " + orderA.getDisCountPrice());

    }
}
