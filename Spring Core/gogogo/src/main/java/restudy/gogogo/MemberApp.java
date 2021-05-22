package restudy.gogogo;

import restudy.gogogo.domain.Grade;
import restudy.gogogo.domain.Member;
import restudy.gogogo.service.MemberService;
import restudy.gogogo.serviceImpl.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member member = memberService.findMember(1L);
        System.out.println("find member = " + member.getName());
        System.out.println("new memberA = " + memberA.getName());
    }
}
