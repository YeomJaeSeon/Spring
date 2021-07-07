package prac.tice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prac.tice.AppConfig;
import prac.tice.domain.Grade;
import prac.tice.domain.Member;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    private MemberService memberService;

    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(findMember).isEqualTo(member);

    }
}