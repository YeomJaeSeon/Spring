package study2.fighting;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study2.fighting.domain.Grade;
import study2.fighting.domain.Member;
import study2.fighting.domain.Order;
import study2.fighting.member.MemberService;
import study2.fighting.member.MemberServiceImpl;
import study2.fighting.order.OrderService;
import study2.fighting.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        // 스프링 컨테이너 생성할때 설정 정보를 같이넘기는데 그게 AppConfig에서 @Configuration 애너테이션붙인 클래스가 섲렁파일이란뜻이고 이를 ㅌ오해서 스프링 컨테이너ㅡ이 스ㅡ링빈이 생성되고(뜽록되고) 의존관계도 설정된다.

        // 스프링 컨테이너는 설정 정보를 토대로 스프링빈이등록되고 스프링 빈 간의 의존관계까 설정된다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 스프링 컨테이너 ApplicationContext를 생성. 이를 스프링컨테이너라고하고 생성할때 자바 설정클래스 정보를 같이넘김 AppConfig.class로
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        // 빈의 key이름으로 빈을 가져온다.

        Member memberA = new Member("memberA", 1L, Grade.VIP);
        memberService.join(memberA);

        Order itemA = orderService.createOrder(1L, "itemA", 20000);

        System.out.println("itemA.getDiscount() = " + itemA.getDiscount());
        System.out.println("itemA.getResult() = " + itemA.getItemSaleResult());
    }
}
