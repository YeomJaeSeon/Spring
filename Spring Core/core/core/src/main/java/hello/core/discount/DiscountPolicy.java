package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /**
     *
     * @return 할인 대상 금액(할인 금액만임. 할인된 가격이아니라.)
     */
    int discount(Member member, int price);
}
