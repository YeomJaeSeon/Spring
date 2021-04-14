package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int itemPrice); // 두번째 파라미터에 아이템가격은 정률 구현체가올경우 필요하므로.
}
