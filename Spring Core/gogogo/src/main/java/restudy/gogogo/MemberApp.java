package restudy.gogogo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import restudy.gogogo.domain.Grade;
import restudy.gogogo.domain.Member;
import restudy.gogogo.service.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member member = memberService.findMember(1L);
        System.out.println("find member = " + member.getName());
        System.out.println("new memberA = " + memberA.getName());
    }
}
