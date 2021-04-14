package restudy.restudy2.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>(); // static으로 클래스가 메모리에올라가면 생성되는 인스턴스 공통 클래스변수로
    // 한 이유는 MemberServiceImpl과 OrderServiceImpl이 MemoryMemberRepository를 사용하는데 singleton이 아니므로 이 클래스는
    // 인스턴스가 계속 해서증가할수있다. 그래서 이 store도 인스턴스 변수(iv)로 선언하면 계속해서 새로운 저장소를 갖게된다. 그래서 static으로
    // 만들었음..! 인스턴스 들의 공통의 변수. static 클래스변수로

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }
}
