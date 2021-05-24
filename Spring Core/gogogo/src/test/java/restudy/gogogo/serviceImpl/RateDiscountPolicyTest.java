package restudy.gogogo.serviceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restudy.gogogo.domain.Grade;
import restudy.gogogo.domain.Member;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10프로 할인이 적용되어야한다.")
    void vip_O(){
        Member member = new Member(1L, "memberA", Grade.VIP);

        int discount = rateDiscountPolicy.discount(member, 20000);

        assertThat(discount).isEqualTo(2000);
    }

    @Test
    @DisplayName("VIP아니면 할인적용 안된다.")
    void vip_X(){
        Member member = new Member(1L, "memberA", Grade.BASIC);

        int discount = rateDiscountPolicy.discount(member, 20000);

        assertThat(discount).isEqualTo(0);
    }
}