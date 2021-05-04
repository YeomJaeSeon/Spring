package hello.core.sudongbean;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SudongBeanTEst {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); // 스프링 컨테이너 생성할때 전달된 설정 클래스도 스프링빈으로 등록된다.

    @Test
    @DisplayName("find all beans")
    void findBeans(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("key = " + beanDefinitionName + ", value = " + bean);
        }
    }

    @Test
    @DisplayName("스프링 빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("스프링 빈 타입으로 조회")
    void findBeanByType(){
        MemberService bean = ac.getBean(MemberService.class);
        System.out.println("bean = " + bean);

        Assertions.assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    }

}
