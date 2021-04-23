package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StateFulServiceTest {


    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);

        // TheadA : A사용자가 10000주문 - 가정상황임.
        stateFulService1.order("userA", 10000);

        //ThreadB : B사용자가 20000원 주문
        stateFulService2.order("userB", 20000);

        //ThreadA : 사용자 A주문금액조회
        int price = stateFulService1.getPrice();
        // 10000나와야함.
        System.out.println("price = " + price);

        Assertions.assertThat(stateFulService1.getPrice()).isEqualTo(20000);
    }

    @Test
    void statelessServiceSingleTon(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig2.class);
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        int itemA = statelessService1.order("itemA", 10000);
        int itemB = statelessService2.order("itemB", 20000);

        System.out.println(itemA); // 10000원임

        Assertions.assertThat(itemA).isEqualTo(10000);

    }

    @Configuration
    static class TestConfig{
        @Bean
        public StateFulService stateFulService(){
            return new StateFulService();
        }
    }

    @Configuration
    static class TestConfig2{

        // 스프링빈. - 싱글턴 객게
        @Bean
        public StatelessService statelessService(){
            return new StatelessService();
        }
    }
}
