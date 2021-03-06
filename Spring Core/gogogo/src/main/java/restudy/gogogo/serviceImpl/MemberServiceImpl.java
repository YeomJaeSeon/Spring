package restudy.gogogo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restudy.gogogo.domain.Member;
import restudy.gogogo.repository.MemberRepository;
import restudy.gogogo.service.MemberService;

@Component
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

    //==테스트 용도==//
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
