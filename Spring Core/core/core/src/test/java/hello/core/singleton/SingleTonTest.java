package hello.core.singleton;

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
    @DisplayName("스프링 없는 순수한 DI컨테이너 - 싱글톤 X")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출 할 떄마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        // 당연히 다른게 생성이된다.. JVM에 계속 객체가 생성이되서 올라감. 정확힌 Memory Heap

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingleTonService singleTonService1 = SingleTonService.getInstance();
        SingleTonService singleTonService2 = SingleTonService.getInstance();

        System.out.println("singleTonService1 = " + singleTonService1);
        System.out.println("singleTonService2 = " + singleTonService2);
        // 당연히 같은객체이다. 싱글톤 객체이므로

        assertThat(singleTonService1).isSameAs(singleTonService2);
        //same : == - 참조비교
        //equal : == equals 메서드로 비교하는거임.(Object클래스의 equals메서드)
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        // 당연히 다른게 생성이된다.. JVM에 계속 객체가 생성이되서 올라감. 정확힌 Memory Heap

        //memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);
        // same : == (참조주소비교)
        // isEqualTo : eqauls(equals메서드 오버라이뒹)
    }
}
