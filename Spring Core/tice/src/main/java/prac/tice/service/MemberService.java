package prac.tice.service;

import prac.tice.domain.Member;

public interface MemberService {

    void join(Member member);
    Member findMember(Long memberId);
}
