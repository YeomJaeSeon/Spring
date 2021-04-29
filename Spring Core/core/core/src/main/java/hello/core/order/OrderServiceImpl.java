package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 스프링 빈의 이름은 클래스의 맨앞글자 소문자로 -> orderServiceImpl로 스프링빈이 등록된다. value는 이클래스의 객체. 즉, 스프링빈으로 등록할때 OrderServiceImpl클래스의 객체를 생성해서 value로, key는 이 클래스의 맨앞글자 소문자로 해서 가지고있다.
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository; // final을 붙이면 상수이다. 즉 값이 있어야하는 필수값이라는걸 언어적으로설정한것(자바언어적으로)
    private final DiscountPolicy discountPolicy;

    // setter주입
    // setter로 의존관계주입은 스프링컨테이너 라이프사이클 2단계 에서일어남. - 스프링빈 등록하고 의존관계 등록할때. 그래서 생성자 주입이 더먼저일어남
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }

    // 생성자주입, 이젠 철저하게 DIP를 지키고있다.
    // 생성자를 통해서 OrderServiceImpl은 MemoryMemberRepository와, FisDiscountPolicy 의존관계가 주입된다. 그러나 얘는 몰라. 그걸 추상화에만 의존하고있으닌까. (완전한 관심사분리)
    // 생성자를 통해서 의존관계를 주입하면 의존관계를 스프링컨테이너 생성될떄 딱한번만 최초에 딱한번만 설정하고 그이후에는 변경하지 않게할수있다. 불변!!!! 이러한 불변이정말중요하다.
    // 실행된이후에 이러한 설정관계, 의존관계가 변경이되면안된다.(연극시작후 배우가 변경되면안되는것처럼, 연극시작될떄 배우는 딱한번만 설정되고 그이후는 변경되면안되는것처럼)
//    @Autowired - 생략가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("constructor"); // 1번 - 스프링 컨테이너 라이프사이클 스프링빈 등록될떄 호출이되면서 의존관계 주입이일어남.
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // * 추가로 생성자가 딱하나일경우 @Autowired 생략가능. - 자주 생략함. 요즘엔

    // 이렇게 public으로 변경할수있도록 열어놓으면 안된다. - 최초에 설정은 딱 한번 할것이기 떄문에(스프링 컨테이너 생성될떄 딱한번.) - setter로하면 불변아아님.
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
