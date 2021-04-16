# 어플전체의 설정을 담당(의존관계를 주입해주는 그 '외부')

```
public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}

```

- 코드가 중복되는 부분도있고 `MemoryMemberRepository`, 역할에 따른 구현이 잘보이지않는다. (역할 - 인터페이스, 에따른 구현 - 인터페이스 구현한 구현체 클래스로 분리되어 보이지않는다.)

# 리팩토링

```
public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}

```

- 코드의 중복을 없앴고, 역할(인터페이스)에 따른 구현(인터페이스 구현한 클래스)로 잘 구분하였다. 이는 인터페이스 다형성으로 인터페이스 구현한 클래스의 객체를 리턴한다는 의미이다.

- 또 하나, 이 AppConfig 클래스만 보면 이제 우리 어플 자체가 전체적으로 어떻게 구성되어있는지 보인다는게 장점이다.

MemberService 역할의 구현은 MemberSErviceImpl이고, MemberRepository역할의 구현은 MemoryMemberRepository이고...
