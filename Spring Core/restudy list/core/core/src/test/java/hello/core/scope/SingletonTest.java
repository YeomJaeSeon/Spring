package hello.core.scope;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 싱글톤 스코프 빈 테스트 - 스프링 컨테이너 스프링빈 등록부터 스프링 빈 소멸까지 전부다 관리함. 스프링빈이 스프링 컨테이너생성 ~ 스프링빈 내려갈떄까지 존재한다.
public class SingletonTest {
    @Test
    void singletonTest(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingleBeanTest.class);
        SingleBeanTest singleBean1 = ac.getBean(SingleBeanTest.class);
        SingleBeanTest singleBean2 = ac.getBean(SingleBeanTest.class);

        System.out.println("singleBean1 = " + singleBean1);
        System.out.println("singleBean2 = " + singleBean2);

        Assertions.assertThat(singleBean1).isSameAs(singleBean2);

        ac.close();

    }

    @Scope("singleton")
    static class SingleBeanTest{
        @PostConstruct
        public void init(){
            System.out.println("SingletonTest.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonTest.destroy");
        }
    }
}
