package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // 컴포넌트 스캔 대상이됨. 자동으로 스프링빈으로 등록
public class OrderServiceImpl implements OrderService{
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // DIP, OCP문제, DIP는 추상화에 의존해야지 구체화에 의존하면안된다는 원칙 못지킴
    // OCP는 코드의 변경이 일어남(기능은 확장, 변경은 닫힘.-원칙 못지킴)

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("1");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long id, String itemName, int itemPrice) {
        System.out.println("id = " + id);
        Member member = memberRepository.findById(id);
        System.out.println("member = " + member);
        int discount = discountPolicy.discount(member, itemPrice);

        return new Order(id, itemName, itemPrice, discount);
    }

    //테스트를 위한 코드

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
