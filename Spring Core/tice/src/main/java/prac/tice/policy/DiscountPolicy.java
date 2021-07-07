package prac.tice.policy;

import prac.tice.domain.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
