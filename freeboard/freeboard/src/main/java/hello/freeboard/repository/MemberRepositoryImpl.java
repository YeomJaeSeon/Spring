package hello.freeboard.repository;

import hello.freeboard.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private static Long sequence = 0L;
    private static Map<Long, Member> store = new HashMap<>();

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member; //저장된 member return
    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
    public Member findById(Long id){
        return store.get(id);
    }

    public void clearStore(){
        store.clear();
    }
}
