package hello.core.singletontest;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {

    @Test
    @DisplayName("다른 객체일까 같은객체일까 스프링 컨테이너는 싱글톤 컨테이너라 같은 객체일까?")
    void configurationSingletonTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        System.out.println("memberService = " + memberService.getMemberRepository());
        System.out.println("orderService = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);
        //다 같다. 싱글톤으로 유지된다.. new로 계속 새로운 객체 생성하는데.. 왜지

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(orderService.getMemberRepository());
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    @DisplayName("스프링 컨테이너 생성될때 스프링빈 메서드 호출된다는데 몇번호출될까?")
    void binCallCountTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // memberRepository메서드만 해도 총 세번호출되어야한다. 총 5번.. (출력되야하는것만 말하는거임.)
        //그런데 총 세번 memberRepository메서드는 한번만호출됐다. 뭐지? 자바코드가 이상한가?
        // 비밀은 바로 스프링이 CGLIB~라는 이상한 바이트코드 조작을통해서 새로운 클래스를 만든다는것
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean.getClass() = " + bean.getClass());
        // 클래스이름이이상하다. CGLIB, 이건 스프링이 클래스를 만드는데 AppConfig클래스 상속한 자식 클래스를 만들고 그걸 스프링빈에 올려놨기에
        // 이런 이상한 클래스이름의 스프링 빈이있는것.
        // 이 클래스가 스프링빈이 싱글톤 객체로 유지되도록 코드를 실행한다.
        // @Configuration (클래스레벨에)이거 뺴면 클래스이름도 내가알던 클래스이름이보이고 스프링빈이 싱글톤으로 유지안됨
        // 메서드 호출만 봐도알수있다.!!!
        // 스프링 컨테이너를 싱글턴 컨테이너로 유지하려면 @Configuration을 빼지 말도록하자.

    }
}
