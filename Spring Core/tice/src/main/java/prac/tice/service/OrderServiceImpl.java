package prac.tice.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import prac.tice.domain.Member;
import prac.tice.domain.Order;
import prac.tice.policy.DiscountPolicy;
import prac.tice.repository.MemberRepository;

@Component
@Getter @Setter
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
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
