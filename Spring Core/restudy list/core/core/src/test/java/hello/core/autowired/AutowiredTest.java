package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
        TestBean bean = ac.getBean(TestBean.class);
        System.out.println("bean.getClass() = " + bean.getClass()); // CGLIB~~

        // Annotation~~ : 스프링 컨테이너 생성(설정 파일은 Test자바 클래스임)
        // TestBean클래스의 객체를 호출하며 기본적으로 스프링빈으로 등록함.(AppConfig)설정정보로 스프링 컨테이너생성할때도 AppCOnfig의 객체를 스프링빈 value로 등록, 이름은 appCOnfig로 등록했다.
    }

    static class TestBean{

        // Autowired가있네?- 이존관계주입해야하는뎅.. - Member는 스프링빈이아니다.. require=true일땐 예외터짐
        @Autowired(required = false)
        void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
