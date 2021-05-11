package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifetCycleTest {

    @Test
    void lifecycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifecycleBean.class);
//        ClientNetwork bean = ac.getBean(ClientNetwork.class);
        ac.close(); // 스프링 컨테이너 닫으려고

    }


    @Configuration
    static class LifecycleBean{
        // 스프링 빈 등록될대 이메서드 호출이됨.. 그러므로
        @Bean(initMethod = "init")
        public ClientNetwork clientNetwork(){
            ClientNetwork clientNetwork = new ClientNetwork();
            clientNetwork.setUrl("www.jaeseon.dev");
            return clientNetwork;
        }
    }
}
