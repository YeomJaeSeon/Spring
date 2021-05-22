package restudy.gogogo.service;

import restudy.gogogo.domain.Member;

public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
}
