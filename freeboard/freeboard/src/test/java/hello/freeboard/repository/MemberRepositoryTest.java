package hello.freeboard.repository;

import hello.freeboard.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {
    MemberRepositoryImpl memberRepository = new MemberRepositoryImpl();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Member member = new Member("member1", "바보223");
        //when
        Member savedMember = memberRepository.save(member);
        //then
        assertThat(member).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1", "123");
        Member member2 = new Member("member2", "234");

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> savedMembers = memberRepository.findAll();

        //then
        assertThat(savedMembers.size()).isEqualTo(2);
        assertThat(savedMembers).contains(member1, member2);
    }

    @Test
    void findById(){
        //given
        Member member1 = new Member("member1", "123");
        Member member2 = new Member("member2", "234");
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        Member findMember = memberRepository.findById(member1.getId());

        //then
        assertThat(findMember).isEqualTo(member1);
    }
}
