package restudy.gogogo.repository;

import restudy.gogogo.domain.Member;

public interface MemberRepository {

    void save(Member member);
    Member findById(Long memberId);
}
