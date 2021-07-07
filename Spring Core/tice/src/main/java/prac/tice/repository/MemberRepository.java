package prac.tice.repository;

import prac.tice.domain.Member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long id);
}
