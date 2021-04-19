package study2.fighting.order;

import study2.fighting.domain.Member;
import study2.fighting.domain.Order;
import study2.fighting.member.MemberRepository;
import study2.fighting.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    MemberRepository memberRepository;
    DiscountPolicy discountPolicy;

    // 생성자를 통해서 의존관계 주입되고있다.
    // 이 클래스는 구현체가 뭔지 아애모름.. 그냥단순히 실행만할뿐임 제어관계가 역전됨 AppConfig로
    // 그래서AppConfig를 IoC컨테이너라고한다.

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discount = discountPolicy.discount(member, itemPrice);
        Order order = new Order(memberId, itemName, itemPrice, discount);

        return order;
    }
}
