package study2.fighting.member;

import study2.fighting.domain.Member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long id);
}
