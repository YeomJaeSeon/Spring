package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); //컨테이너 내리기(끝내기) -> 스프링 컨테이너에 등록된 스프링빈들도 죽는다.
    }

    @Configuration
    static class LifeCycleConfig{
        // 여담으로 수동으로 스프링빈 등록할때 이 메서드의 이름이 스프링빈의 key로들어간다. 컴포넌트 스캔은 클래스이름 첫글자 소문자가 스프링빈의 이름(key)로 등록되었다.
        @Bean // 종료할때 자동추론. @Bean의 destroyMethod="(inferred)" - default임.
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
