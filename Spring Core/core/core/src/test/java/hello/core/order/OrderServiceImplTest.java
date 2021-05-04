package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

public class OrderServiceImplTest {

    // setter주입 할때 자바로 테스트할경우. -> 의존관계 눈에보이지않음.
    // 생성자주입으로하면 의존관계 눈에보임
    @Test
    void orderTest(){
        Member member = new Member(1L, "memberA", Grade.VIP);
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(member);
        //생성자 주입을 사용하면 의존관계가 테스트할때 확인하기 쉽다.(자바만으로 단위테스트할떄.)
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        // createOrder메서드를 사용할때, 의존관계 가눈에보임? 코드 들어가면 memberRepository, discountPoliy멤버를 사용하는데 Null이므로 NPE예외 터진다.
        Order itemA = orderService.createOrder(1L, "itemA", 20000);
        System.out.println("itemA.getDiscountPrice() = " + itemA.getDiscountPrice());
        Assertions.assertThat(itemA.getDiscountPrice()).isEqualTo(1000);

    }

    @Test
    @DisplayName("setter주입시 테스트")
    void setterTest(){
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        // createOrder메서드내에서 memberRepository, discountPolicy가 null이므로 NPE 예외 터짐
//        // setter주입하면 뭐 의존관계까 한눈에보이지 안흔다. 직접 넣어줄순있어
//        Member member = new Member(1L, "memberA", Grade.VIP);
//        MemberRepository memberRepository = new MemoryMemberRepository();
//        memberRepository.save(member);
//
//        // 아래두줄로 이렇게 의존관계 setter로 주입할순있는데 의존관계 눈에보이나? 무슨말이냐면
//        // 처음에 테스트할때 의존관계까 한번에 눈에보이지않아. 직접 OrderServiceImpl클래스에 들어가서 확인을해야함.
//        // 생성자 주입을 하면 바로눈에보임. 자바만으로 테스트할때(단위테스트) 생성자에 의존관계 안넣으면 컴파일 에러가 뜬다.
//        // 컴파일에러는 좋은에러이고 뜬다는거자체가 한눈에 뭘넣어야하는지 뭘안넣엇는지 알수있다. 이는 에러를 줄일수있다는 뜻이다.
//        // 생성자주입은 의존관계도 처음에 스프링빈으로등록, 객체생성될떄 딱 한번만 섲렁되고 그뒤론 변경할수도없으므로(뭐 강제적으로 변경하려면할수있지만..)
//        // 불면하므로 또 public으로 언제나 접근할수있는 setter보다 좋다
//        // 그래서 왠많마ㅕㄴ 무조건 생성자주입을 쓰자.
//        orderService.setDiscountPolicy(new FixDiscountPolicy());
//        orderService.setMemberRepository(memberRepository);
//
//        Order itemA = orderService.createOrder(1L, "itemA", 30000);
//
//        Assertions.assertThat(itemA.getDiscountPrice()).isEqualTo(1000);
    }
}
