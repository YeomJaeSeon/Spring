package restudy.restudy2.member;

public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 구현체가 바뀌면
    // 변경되야함 - OCP 원칙X
    // 추상화에 의존해야지 구체화에 의존하면 안된다는 DIP(의존관계 역전의원칙) 못지킴! 다형성 인터페이스로 역할 구현, 선언 구현을
    // 분리하여서 변경에 유리한 코드로 설계해도 뭔가부족하다..

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findName(Long id) {
        return memberRepository.findById(id);
    }
}
