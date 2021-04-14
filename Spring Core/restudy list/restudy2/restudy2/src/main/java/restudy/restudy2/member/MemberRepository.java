package restudy.restudy2.member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long id);
}
