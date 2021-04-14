package restudy.restudy2.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {
    MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
        Member member = new Member(1L, "memberA", Grade.BASIC);

        memberService.join(member);
        Member findMember = memberService.findName(1L);

        Assertions.assertThat(findMember).isEqualTo(member);

    }

}