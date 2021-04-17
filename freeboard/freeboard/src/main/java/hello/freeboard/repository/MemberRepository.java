package hello.freeboard.repository;

import hello.freeboard.domain.Member;

import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    List<Member> findAll();
    Member findById(Long id);
    void clearStore();
}
