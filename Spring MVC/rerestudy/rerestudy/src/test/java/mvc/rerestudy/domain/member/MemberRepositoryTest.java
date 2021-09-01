package mvc.rerestudy.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원_저장(){
        Member member = new Member("hello", 30);

        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void 모든_회원_찾기(){
        Member member = new Member("member1", 20);
        Member member1 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member);

        List<Member> result = memberRepository.findAll();
        //jpql만들때 자동으로 flush되므로 이때 select 쿼리가나가는것,!

        assertThat(result).contains(member, member1);
        assertThat(result.size()).isEqualTo(2);
    }

}