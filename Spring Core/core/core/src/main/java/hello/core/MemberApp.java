package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// junit안쓰고 테스트하는거임.
public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // 이게 Spring컨테이너임 - 인터페이스임                   해당인터페이스 구현한 구체 클래스임.     요로케 스프링 컨테이너생성할때 설정 정보를 같이전달한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // 참고로 스프링 컨테이너 생성할때 설정정보는 자바 클래스파일또는 XMl로 만들수있다.


        Member memberA = new Member(1L, "memberA", Grade.BASIC);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("memberA = " + memberA.getName());
        System.out.println("findMember = " + findMember.getName());
        System.out.println(memberA == findMember);
    }
}
