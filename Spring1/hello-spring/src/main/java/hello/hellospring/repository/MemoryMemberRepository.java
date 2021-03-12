package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository annotation을 줘야 스프링이 아 이건 Repository라는거구나라는걸 안다.
// Controller, Service, Repository : 되게 정형화된 패턴.
// Controller : 이걸 통해서 외부요청받고
// Service : 비즈니스 로직만들고
// Repository: 데이터를 저장하고.
//@Repository
public class MemoryMemberRepository  implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
//    동시성 문제로 실무에선 conCurrenthashmap사용한다
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
//    Null이 반환될 가능성이있으므로 Optional.ofNullable을 이용한다.
    public Optional<Member> findById(Long id) {
            return Optional.ofNullable(store.get(id));
    }

//    Map(store)에서 하나라도찾으면 반환, 없으면 Optional이므로 null 반환
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

//    List사용, List에 모두찾은내용담아..
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}

//이게 동작잘하는지 안하는지 확인해보자 -> Test Case 를 작성해보자.
