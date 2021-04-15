package restudy.restudy2.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import restudy.restudy2.member.Grade;
import restudy.restudy2.member.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    void discount(){
        Grade grade = Grade.VIP;

        int discount = rateDiscountPolicy.discount(grade, 10000);
        int discount2 = rateDiscountPolicy.discount(grade, 20000);

        assertThat(discount).isEqualTo(1000);
        assertThat(discount2).isEqualTo(2000);
    }

}