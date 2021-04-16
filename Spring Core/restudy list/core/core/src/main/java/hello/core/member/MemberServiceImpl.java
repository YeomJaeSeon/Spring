package hello.core.member;

public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository;
    //인터페이스 다형성으로 MemberServiceImpl은 역할인 인터페이스만 의존하고있다. 이는 역할과 구현으로 분리하여 구현을 쉽게 변경할수있는 유연한 코드로 설계한ㄱ더ㅏ
    //애초에 요구사항중 저장소가 바뀔슁ㅆ다는 요구사항이있었음..

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findName(Long id) {
        return memberRepository.findById(id);
    }
}
