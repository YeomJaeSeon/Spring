package restudy.restudy2.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restudy.restudy2.AppConfig;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {
    MemberService memberService;

    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        Member member = new Member(1L, "memberA", Grade.BASIC);

        memberService.join(member);
        Member findMember = memberService.findName(1L);

        Assertions.assertThat(findMember).isEqualTo(member);

    }

}