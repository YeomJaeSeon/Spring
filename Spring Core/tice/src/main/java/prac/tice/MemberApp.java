package prac.tice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import prac.tice.domain.Grade;
import prac.tice.domain.Member;
import prac.tice.service.MemberService;
import prac.tice.service.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService bean = ac.getBean(MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        bean.join(member);

        Member findMember = bean.findMember(1L);

        System.out.println("new Member : " + member.getName());
        System.out.println("find MEmber : " + findMember.getName());
    }
}
