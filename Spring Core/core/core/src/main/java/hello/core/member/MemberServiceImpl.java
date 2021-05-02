package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 생성자 주입, 이젠 MemberServiceImpl에서 MemoryMemberRepository가 하나도없다. 추상화에만 의존 -> DIP지킴!
    // 의존관계주입됨. Dependency Injection
    @Autowired // 생성자가 하나일경우 생략가능.!
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
