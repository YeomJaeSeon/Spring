package study2.fighting.member;

import study2.fighting.domain.Member;

public class MemberServiceImpl implements MemberService{
    // DIP OCP도 이젠완전히지킴. 다형성 인터페이스로 부족했던 DIP OCP를 관심사 분리하면서 완전히 지킴
    MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }
}
