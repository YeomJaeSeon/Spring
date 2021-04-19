package study2.fighting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study2.fighting.domain.Member;
import study2.fighting.member.MemberRepository;
import study2.fighting.member.MemberService;
import study2.fighting.member.MemberServiceImpl;
import study2.fighting.member.MemoryMemberRepository;
import study2.fighting.order.DiscountPolicy;
import study2.fighting.order.FixDiscountPolicy;
import study2.fighting.order.OrderService;
import study2.fighting.order.OrderServiceImpl;

// IoC컨테이너 혹은 DI컨테이너라고하낟.
@Configuration // 자바 설정 클래스라는 뜻
public class AppConfig {
    @Bean // 메서드가 호출되어 리턴된 객체를 스프링 빈의 value로 등록한다. (쓰프링 컨테이너내의) 메ㅓㅅ드이름을 key로
    public OrderService orderService(){
        return new OrderServiceImpl(memoryRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
    @Bean
    public MemberRepository memoryRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memoryRepository());
    }
}

/**
 * 클라이언트코드에서 OCP DIP를 지키고싶어서 관심사분리를 통해서 객체를 생성하고 의존관계를 맺어주는 역할을 하는 AppCOnfig
 * 클래스를 만들었따. 만들었더니 OCP DIP를 지켰다.
 * 코드를 좀살펴보면 MemberServiceImpl, OrderServiceImpl같은 클라이언크ㅗ드는 구현체를 아애모르지만 해당 실제 객체가 생성ㄷ리때 생성자로 의존관계가 주입된다. AppConfig를 통해서
 * 해당 클래스 자체는 제어권이업ㅇ슴. 구현체가 뭔지도모름.. -> 제어의역전 이되었다고하괴 AppConfig를 IoC컨테이너라고한다.
 * 그리고 생성자를 ㅌ통해서 어ㅡㄹ리케이션이 실행될때 동적으로 객체가 주입되므로이를 의존관계 주입 DI라괗고 의존관계 주입해주는 AppConfig를 DI컨테이너라고한다.
 * 이렇게 클라인언트코드는 이제 단순히 실행만하고 AppConfig는 객체를 생성하고 의존관계를 설저앻주낟.
 * 즉, 실행부분과 서렂ㅇ(구성)뿌분으로 완전히 관심사가 분리되었따..
 *
 * DIP OCP를 지키게됨..!!!! 스프링으로 전환해보자.
 */
