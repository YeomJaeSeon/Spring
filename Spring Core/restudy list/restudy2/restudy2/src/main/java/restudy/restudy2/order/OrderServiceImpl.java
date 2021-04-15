package restudy.restudy2.order;

import restudy.restudy2.discount.DiscountPolicy;
import restudy.restudy2.discount.FixDiscountPolicy;
import restudy.restudy2.discount.RateDiscountPolicy;
import restudy.restudy2.member.Member;
import restudy.restudy2.member.MemberRepository;
import restudy.restudy2.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    // 생성자를 통해서의존관계 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discount = discountPolicy.discount(member.getGrade(), itemPrice);

        return new Order(memberId, itemName, itemPrice, discount);
    }
}
