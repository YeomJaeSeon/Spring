package restudy.restudy2.discount;

import restudy.restudy2.member.Grade;

public interface DiscountPolicy {
    int discount(Grade grade, int itemPrice);
}
