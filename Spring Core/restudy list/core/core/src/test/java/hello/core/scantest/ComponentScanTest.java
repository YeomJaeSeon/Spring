package hello.core.scantest;

import hello.core.AutoAppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentScanTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class); // 이렇게 스프링 컨테이너 생성할때
    // 설정파일로 넘긴녀석은 스프링빈으로 무적건등록이됨. 수동으로 스프링 빈등록하는 AppConfig나, 자동으로 스프링 빈 등록하는(컴포넌트 스캔방식) AutoAPpCOnfig나.

    @Test
    @DisplayName("컴포넌트 스캔방식으로 스프링빈등록")
    void scanTest(){
        // @Bean으로 나열해서 스프링빈 등록하지 않고 컴포넌트 스캔방식으로 자동으로 스프링 빈 등록
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("key : " + beanDefinitionName + ", value : " + ac.getBean(beanDefinitionName));
        }
    }
}
