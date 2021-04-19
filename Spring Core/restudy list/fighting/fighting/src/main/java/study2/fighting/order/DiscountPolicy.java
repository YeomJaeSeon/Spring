package study2.fighting.order;

import study2.fighting.domain.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
