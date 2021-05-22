package restudy.gogogo.service;

import restudy.gogogo.domain.Member;

public interface DiscountPolicy {
    /**
     * @return 할인금액
     */
    int discount(Member member, int price);
}
