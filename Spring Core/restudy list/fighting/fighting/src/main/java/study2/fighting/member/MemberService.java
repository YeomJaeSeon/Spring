package study2.fighting.member;

import study2.fighting.domain.Member;

public interface MemberService {
    void join(Member member);
    Member findMember(Long id);
}
