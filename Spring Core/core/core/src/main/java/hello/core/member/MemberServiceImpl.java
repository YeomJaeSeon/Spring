package hello.core.member;

// 관례적으로 인터페이스에대한 구현체가 하나면 이름을 Impl을 뒤에붙임.
public class MemberServiceImpl implements MemberService{

    // 다형성 - 부모타입의 참조변수로 자식인스턴스접근. 인터페이스와 다형성으로 역할 구현분리
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
