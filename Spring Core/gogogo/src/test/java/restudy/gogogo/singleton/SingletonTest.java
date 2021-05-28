package restudy.gogogo.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import restudy.gogogo.AppConfig;
import restudy.gogogo.AppConfig2;
import restudy.gogogo.service.MemberService;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너 - 싱글턴 X")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();

        MemberService memberService1 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService = " + memberService);

        // 싱글턴 유지못한다.
        assertThat(memberService).isNotSameAs(memberService1);
    }

    @Test
    @DisplayName("싱글턴 패턴 도입한 객체 사용")
    void singletonServiceTest(){
        SingletonService instance = SingletonService.getInstance();
        SingletonService instance1 = SingletonService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance = " + instance);

        // sameAS : ==
        // isEqualt : equals();
        assertThat(instance).isSameAs(instance1);
    }

    @Test
    @DisplayName("AppConfig 싱글턴 으로 변경")
    void sigletonTest2(){
        MemberService memberService1 = AppConfig2.memberService();
        MemberService memberService2 = AppConfig2.memberService();
        // 실제 인스턴스는 하나지만 해당 인스턴스의 주소를 가지는 참조변수는 많다.
        // 객체 생성보다 이게 훨 비용이 덜듬. 그래서 싱글턴 패턴사용.

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }

    @Test
    @DisplayName("스프링컨테이너오 싱글턴")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService = " + memberService);

        // 싱글턴 유지못한다.
        assertThat(memberService).isSameAs(memberService1);
    }
}
