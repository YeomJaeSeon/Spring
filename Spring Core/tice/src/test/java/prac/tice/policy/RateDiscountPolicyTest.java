package prac.tice.policy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import prac.tice.domain.Grade;
import prac.tice.domain.Member;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    void rateDiscount(){
        Member member = new Member(1L, "memberA", Grade.VIP);

        int discount = discountPolicy.discount(member, 30000);

        Assertions.assertThat(discount).isEqualTo(3000);
    }

}