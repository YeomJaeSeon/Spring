package prac.tice.beans;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import prac.tice.AppConfig;
import prac.tice.repository.MemberRepository;
import prac.tice.service.MemberService;
import prac.tice.service.MemberServiceImpl;
import prac.tice.service.OrderService;
import prac.tice.service.OrderServiceImpl;

public class SelectBeans {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든빈출력")
    void findAllBeans(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name : " + beanDefinitionName + ", bean : " + bean);
        }
    }

    @Test
    void noCGLIB(){
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println(bean.getClass());
        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        System.out.println("memberService. = " + memberService.getMemberRepository());
        System.out.println("orderServie = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);
    }
}
