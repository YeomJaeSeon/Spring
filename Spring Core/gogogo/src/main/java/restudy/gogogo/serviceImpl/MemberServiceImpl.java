package restudy.gogogo.serviceImpl;

import restudy.gogogo.domain.Member;
import restudy.gogogo.repository.MemberRepository;
import restudy.gogogo.service.MemberService;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository){
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
}