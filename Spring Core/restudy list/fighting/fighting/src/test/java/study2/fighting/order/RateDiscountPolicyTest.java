package study2.fighting.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study2.fighting.domain.Grade;
import study2.fighting.domain.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP o")
    void vip_o(){
        Member memberA = new Member("memberA", 1L, Grade.VIP);
        int discount = rateDiscountPolicy.discount(memberA, 20000);

        assertThat(discount).isEqualTo(2000);
    }

    @Test
    @DisplayName("vip x")
    void vip_x(){
        Member memberA = new Member("memberA", 1L, Grade.BASIC);
        int discount = rateDiscountPolicy.discount(memberA, 20000);
        assertThat(discount).isEqualTo(0);

    }

}