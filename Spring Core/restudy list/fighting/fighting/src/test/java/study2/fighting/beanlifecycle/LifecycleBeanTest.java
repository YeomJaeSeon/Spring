package study2.fighting.beanlifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class LifecycleBeanTest {

    @Test
    @DisplayName("초기화콜백, 소멸전 콜백 테스트")
    void test(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(BeanLifeCycle.class); // 설정정보로 스프링 컨테이너생성 -> 스프링빈등록-> 스프링빈간의 의존관계주입
        // -> 초기화콜백 -> 빈 사용 -> 소멸전콜백 -> 빈 소멸(스프링 컨테이너 종료)
        ac.close();

    }

    @Configuration
    static class BeanLifeCycle{

        // 수동빈 등록 빈의 key는 메서드이름
        @Bean // 스프링 빈등록될때 clientNetwork메서드 호출됨 그때 setUrl로 ClientNetwork객체의 url 멤버변수값이 등록이되고 @PostConstruct때, 해당 url참조하는 메서드들이 실행되서 url이뜨는거고
        // @PreDestroy도 스프링빈 등록된 url 멤버변수를 참조하며 disconnect()메서드가 호출되는 것임르ㅗ url이 잘나온다.
        //스플이빈 lifecycle, 스프링비 ㄴ등록딜떄 해당 메서드 호출되낟는 것만알면 이해할수있다. 흐름을 이해하자.
        public ClientNetwork clientNetwork(){
            ClientNetwork clientNetwork = new ClientNetwork();
            clientNetwork.setUrl("www.naver.dev");
            return clientNetwork; // 스프링 빈 value로 등록될 예정
        }
    }
}
