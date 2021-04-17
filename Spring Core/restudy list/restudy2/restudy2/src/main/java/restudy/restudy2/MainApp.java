package restudy.restudy2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import restudy.restudy2.member.Grade;
import restudy.restudy2.member.Member;
import restudy.restudy2.member.MemberService;

public class MainApp {
    public static void main(String[] args) {
        // applcationContext : 스프링 빈들이 등록되어져있는 스프링 컨테이너이다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findName(1L);

        System.out.println(member.getMemberName());
        System.out.println(findMember.getMemberName());
    }
}
