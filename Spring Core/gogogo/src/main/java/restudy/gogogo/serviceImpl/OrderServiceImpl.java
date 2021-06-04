package restudy.gogogo.serviceImpl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import restudy.gogogo.annotation.MainDiscountPolicy;
import restudy.gogogo.domain.Member;
import restudy.gogogo.domain.Order;
import restudy.gogogo.repository.MemberRepository;
import restudy.gogogo.service.DiscountPolicy;
import restudy.gogogo.service.OrderService;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //==테스트 용도==//
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

/**
 생성자 주입이 좋은 이유
 1. 의존관꼐는 어플 시작하는 시점에왠만하면 변경안됨. - 수정자주입으로 의존관계 열어넣는건 좋지않다.
 2. 누락을 막아준다. 생성자를 통해서의존관계를 주입받으면 외부에서 주입받는 의존관계 누락을 막아줌. - 컴파일 에러가 나서 (수정자 주입과 비교해보았따.)
 3. final키워드 사용 - 불변이므로 final자체만으로도 의미가있지만, 컴파일에러로 혹시모를 생성자주입에서의 DI 누락을 막아줌.
 -> 컴파일 에러는 세상에서 제일좋은에러. 런타임 에러는 안좋은 에러
 **/