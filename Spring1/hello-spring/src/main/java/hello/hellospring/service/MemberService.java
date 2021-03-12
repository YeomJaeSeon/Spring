package hello.hellospring.service;

// 서비스 클래스 개발
// 서비스는 리포지토리, 도메인 이용해서 실제 비즈니스 로직 작성.

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//서비스 클래스 테스트하고싶은데 전처럼 다 작성해야할까? 노노
//미친 커맨드있음 ctrl shift T

//@Service로 annotation주면 스프링이 아 이녀석은 Service로 스프링컨테이너에 등록을 시키면
//되겠구나 하고 등록된다. 그다음 @Autowired가 있는 생성자가 호출되면 스프링컨테이너에 이미
//등록되어있는 MemberService를 가져다가 연결을 한다.
//@Service
public class MemberService {
    private final MemberRepository memberRepository;

//   alt insert 누르면 constructor , getter setter 등 자동완성
//    constructor임(생성자) memberRepository를 외부에서 주입받지. 이런걸 DI라고한다...!
//    MemberService에서 memeberRepository객체를 생성하지않으므로. 외부에서 주입받으므로
//    Dependency Injection, DI라고한다. 아래 보면 memberRepository를 memberService
//    에서 생성하지않고 외부에서 받아서 주입한다..
//    @Autowired
//    @Autowired이므로 스프링은 스프링 컨테이너에있는 memberRepository를 서비스에 넣어준다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
//        같은 이름이 있는 중복 회원X다는 룰을 만듬
//      아주 유용한 단축키 ctrl alt v 하면 리턴을 걍해줌.
        validateDuplicateMember(member);
//        ctrl alt M으로 메서드 개간단하게 만들수있음

        memberRepository.save(member);
        return member.getId();
    }

//    회원가입시 중복된 이름이 있으면 안됨.
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
//    서비는 좀더 서비스스럽게 이름을 지음. 리포지터리보다 더 직관적이게, 이게 무슨 기능을
//    의미하는지 바로 알수 있게
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
