package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        this.memberService = appConfig.memberService();
    }


    @Test
    void join(){
        Member member = new Member(1L, "MemberA", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findName(1L);

        Assertions.assertThat(findMember.getMemberName()).isEqualTo("MemberA");
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
