package js.com.repository;

import js.com.member.Member;
import org.springframework.stereotype.Repository;

import java.util.Vector;

@Repository
public class MemberRepository {
    private static Vector<Member> store = new Vector<>();


    public void save(Member member){
        store.add(member);
    }

    public Vector<Member> findAll(){
        return store;
    }
}
