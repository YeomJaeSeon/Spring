package hello.hellospring.service;
//만들어지는 위치도 예술임.

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // memberRepository(serviceTest의) 와 service의 memberRepository가 다른객체이다.
    // 다른 객체를 굳이만들필요도없고 같은 객체를 테스트하는게 더맞는거지. 하나의 같은 리파지토리를
    // 테스트 하는게 더 적절하다.
    // 같은 객체 사용하도록 변경경

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

// 이것도 테스트끝나면 저장소 비워주는 과정임.
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
//    test는 이름을 그냥 한글로 바꿔도됨. production코드 같은 건 한글이좀그런데
//    테스트는 한글로적어도됨. build될때 테스트코드는 실제코드에 포함안되닌까 가능함.
    void 회원가입() {
//        given : 뭔가가 주어졌을 때
        Member member = new Member();
        member.setName("hello");

//        when : 이걸 실행했을때
        Long saveId = memberService.join(member);

//        then : 이러한 결과가 나와야함.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

//    테스트는 위 회원가입처럼 정상flow도 중요하지만 예외 flow도 훨씬 중요하다.
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
//        좀 깔끔한 방법임. 예외처리 테스트하는방법 - assertThrows
//        뒤인자 로직이 실행되면 앞 에러가 발생해야한다.(asserThrows)
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try{
//            memberService.join(member2);
//            fail();
//            // fail은 catch로 안갔을때 fail. 예외처리가 잘되지않을때.. 즉, test실패경우겠지
//        }catch (IllegalStateException e){
//            // 이경우는 테스트 성공, 예외처리가 잘되었다.
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
//        try catch도 좋지만 다른 방법도있음.


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}