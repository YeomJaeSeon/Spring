package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // 스프링 빈의 이름은 클래스의 맨앞글자 소문자로 -> orderServiceImpl로 스프링빈이 등록된다. value는 이클래스의 객체. 즉, 스프링빈으로 등록할때 OrderServiceImpl클래스의 객체를 생성해서 value로, key는 이 클래스의 맨앞글자 소문자로 해서 가지고있다.
//@RequiredArgsConstructor // final이 붙은 멤버변수가있으므로 required 멤버변수에대한 생성자도 롬복이 만들어줌 - final이붙은 멤버변수에 대한 생성자
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository; // final을 붙이면 상수이다. 즉 값이 있어야하는 필수값이라는걸 언어적으로설정한것(자바언어적으로)
    private final DiscountPolicy discountPolicy;

    // @Autowired - 생성자하나이면 생략가능.
    // Autowired 1. 타입에맞는 빈 찾아서의존관계 ㅈ윕
    // 2. 타입에맞는 빈이 두개이상이면 필드명, 파라미터 명을 보고 주입
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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
