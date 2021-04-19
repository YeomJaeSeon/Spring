package study2.fighting;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study2.fighting.domain.Grade;
import study2.fighting.domain.Member;
import study2.fighting.member.MemberService;
import study2.fighting.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();
        // ApplicationContext가 스프링 컨테이너인데 이는 인터페이스, Annot~ 이는 해당인터페이스 구현체이다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member("memberA", 1L, Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        System.out.println(member.equals(findMember));
    }
}
