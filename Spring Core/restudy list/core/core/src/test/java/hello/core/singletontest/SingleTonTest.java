package hello.core.singletontest;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingleTonTest {

    @Test
    @DisplayName("그냥 AppConfig를 통해 객체 생성하기")
    void pureConfig(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        // 당연히 다른 객체임

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴으로 싱글톤 객체")
    void singleTonTest(){
        SingleTon instance1 = SingleTon.getInstance();
        SingleTon instance2 = SingleTon.getInstance();

        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("스프링 컨테이너의 싱글톤 컨테이너사용")
    void springConfig(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 스프링 컨테이너는 싱글톤 컨테이너이다. 싱글톤 컨테이너에 등록되어있는 스프링 빈들은 싱글톤 객체로 존재함. 이를 공유하면서 사용
        // 이로써 싱글톤 패턴의 문제 해결과 하나의 객체를 공유하며 사용하는 장점 두마리 토끼둘다 갖게됨
        // 스프링 컨테이너는 싱글턴 컨테이너 역할을 함으로써 스프링빈을 하나의 객체만 유지할수있다다

       assertThat(memberService).isSameAs(memberService1);
    }
}
