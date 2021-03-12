package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

//test case를 클래스레벨에서 돌리면 전체 테스트 가능..
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

//    test메소드 하나 끝나고 실행하는 콜백메소드임.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

//    Test하는 법.
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

//        Optional에서 데이터꺼내는법. get(좋은 방법아님, 테스트케이스닌까 그냥한거임)
        Member result = repository.findById(member.getId()).get();
//        Assert기능이있음 system.out.println이런 출력말고 assertion으로 테스트 가능
//        Assertions.assertEquals(member, result);
//        에러면 빨간불뜸, 맞으면 초록불
        assertThat(member).isEqualTo(result);
//        static import 사용가능 alt enter
//        이방식으로 이용하면 위 asserEquals보다 직관적임.
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

//        shift f6으로이름 한꺼번에 바꿀수있음
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

//    테스트케이스 작성할땐 테스트 메소드 순서에 무관해야한다.
//    그래스 테스트 끝나면 데이터 clear해야한다.
//    해결방법으론 AfterEach콜백 메소드를 이용해서 테스트 메소드 하나끝날때마다 실행하도록하는데
//    해당 콜백메소드는 MemoryMemberRepository의 clearStore를 이용.
//    테스트끼리는 의존관계가 없이 설계되어야한다.
//    하나의 테스트가 끝날때마다 저장소나 공용데이터를 깔끔하게 지워줘야 한다.! 무조건

//    테스트 부터 작업할수있따. -> 테스트 주도 개발(TDD) 우린 테스트를 나중에함.
//    테스트 관련해선 무조건 깊이있게 공부해야함.

}
