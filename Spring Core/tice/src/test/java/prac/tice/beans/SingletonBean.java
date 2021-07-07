package prac.tice.beans;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import prac.tice.AppConfig;
import prac.tice.service.MemberService;
import prac.tice.service.MemberServiceImpl;

public class SingletonBean {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void test(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Object memberService1 = ac.getBean("memberService");
        MemberService bean = ac.getBean(MemberService.class);
        MemberServiceImpl bean1 = ac.getBean(MemberServiceImpl.class);
        System.out.println(memberService);
        System.out.println(memberService1);
        System.out.println(bean);
        System.out.println(bean1);

    }
}
