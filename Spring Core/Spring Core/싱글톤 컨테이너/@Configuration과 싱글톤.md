# 의문

```
@Configuration
public class AppConfig {

    // 반환타입이 인터페이스인건 다형성을 통해서 구현체를 휙휙 쉽게 바꾸기위해서 만약 반환타입이 MemberServiceImpl이였으면 구현체를 바꿀대 반환타입도 바꿔야함. 이렇게 다형성을 이용하는것이중요하다.
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 스프링빈
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository(); // 실제 메서드가 호출한 결과인 이 객체가
        // 스프링 빈의 value로 등록되어있다. 즉, 스프링 빈으로 등록되어있다. 이 객체들이
    }

    // 메서드 이름은 스프링 빈의 key로 등록되어져있음 단순히 스프링 value에 접근하기 위한 이름일뿐.
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy()); //스프링 빈
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    // Spring 컨테이너라는곳에 다 등록이됨 @Bean하면.
}
```

- 스프링 컨테이너는 싱글턴 컨테이너로 스프링빈 객체를 단 하나만 생성해서 공유하도록 한다고했는데 자바코드를 보면 memberService메서드가 리턴하는 `MemberServiceImpl` 객체 생성자 매개변수로 `memberRepository`메서드를 호출하는걸 볼수가있다. `memberRepository`메서드는 `MemoryMemberRepository`객체를 리턴한다.
- 또, `orderService`메서드는 `OrderServiceImpl`객체를 리턴하는데 생성자 매개변수로 `memberRepository`메서드의 반환값을 전달한다.
- `memberRepository`메서드의 반환값이 `MemberMemberRepository`객체인데 new를 두번썼으니 당연히 MemoryMemberRepository객체가 두개 생성되야하는게 맞는거아닌가?
- 스프링 컨테이너 생성될때 `@Bean`붙인 메서드가 호출되서 스프링빈으로 등록된다했는데 그렇게 따지면 `memberRepository`메서드가 총 세번호출되서 각각 다른 세개의 `MemoryMemberRepository`객체를 가지고있어야 하는게 아닌가?
- 테스트로 확인해보자

# 확인

- `MemberRepositoryImpl` 클래스가 의존하는 추상화(인터페이스)에 실제로 어떤 구현체가들어가는지, `OrderServiceImpl` 클래스가 의존하는 추상화(인터페이스)에 실제로 어떤 구현체가 들어가는지를 확인해보자. 둘다 `MemoryMemberRepository`객체가 들어가는데 다른 객체가 생성되는지, 아니면 싱글턴 컨테이너답게 뭔 방식인진 모르겠는데 싱글턴 객체로 딱하나만 생성해서 공유하는지..

```
// memberServie와 orderService는 스프링빈임.
//getMember~메서드는 추상화에 들어가는 구현체 객체 확인하는 메서드임.
//(MemoryMemberRepository객체.)
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
```

- 같은객체다. 즉, 참조변수가 같은 객체의 주소를 가르킨다. 어떻게 이럴수있지?
- 스프링 컨테이너 생성되면서 `@Bean`메서드들 호출해서 리턴되는 값을 스프링빈으로 등록하는데 각 메서드들이 `memberRepository`메서드를 통해 `new MemoryMemberRepository`를 총 세번 호출하는데 어떻게 같은 객체일수가있지?
- 그비밀은 `@Configuration`에있다.
- 그전에 AppConfig 스프링빈의 클래스이름을 확인해보자 (getClass()메서드로)

# AppConfig스프링빈 클래스이름

```
    @Test
    @DisplayName("AppConfig 스프링 빈 클래스이름 확인")
    void appConfigClassName(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
    }
```

- 분명히 패키지이름 포함되서 `~.~.AppConfig`가 나와야 정상이다.(패키지 이름까지 포함한게 클래스의 full name.. 자바기본.!)
- 그런데 `class hello.core.AppConfig$$EnhancerBySpringCGLIB$$f78f2811`라고 요상한 녀석이나왔다.
- 즉 내가 생각한 AppConfig클래스가아니라 다른클래스인것이다.
- 이건 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 이용해서 AppConfig를 **상속**받은 새로운 클래스(저 요상한이름의.)를 만들고 그 클래스를 스프링 빈으로 등록했기에 스프링빈의 클래스이름을 출력하닌까 저게나온거다.
- 즉 내가 만든 AppConfig클래스가 아니라 다른 클래스,(내가만든 AppConfig를 상속받은 자식 클래스) 가 스프링 빈으로 등록되어진거다.
- 이건 스프링이 행한 동작.!
- 바로 이 클래스가 , AppConfig를 상속받은 새로운 클래스가 스프링빈 객체들이 싱글턴 객체가 되도록 유지해준다..

# AppConfig 상속받은 새로운 클래스!의 동작

- 상속받을때 오버라이딩이된 메서드들이 다르게 동작해서(내가 짠 코드와) 싱글턴 객체를 가지고있는 것이다.
- 어떤식으로 가지고있을까?

1. 만약 스프링 컨테이너에 이미 등록되어져있는 스프링빈이면 스프링 빈 그냥 반환
2. 그렇지 않다면? (스프링 컨테이너에 등록이 되어져있지 않다면) 스프링 컨테이너에 스프링빈으로 최초로 하나 딱 등록하고 그걸 반환.

- 위 1, 2동작에의해 이미 등록된 스프링빈이면 1번에서 걸러져서 새로운 스프링빈을 생성하지않고 딱하나의 객체 인스턴스만 생성하게되어 싱글턴을 유지할수있다.
  (이 동작보다는 훨씬 복잡하다고한다.)

- AppConfig를 상속받은 새로운 클래스는 `@Configuration`을붙이면 생성되고 없으면 새로운 클래스가 생성되지않고 우리가 만든 AppConfig클래스만 존재하게되고 우리가 예상한대로 스프링빈은 싱글톤으로 존재하지 않게된다.
- 확인해보자

# @Configuration 없애보기

- 없애고 AppConfig 스프링빈의 클래스이름 출력해보닌까 `class hello.core.AppConfig` 라고 나온다.
- 그런데 위에서 짠 코드의 객체는 어떻게됐을까? -> (예상한대로 다 다른 객체의 주소를 가지고있다.)
- 즉, 스프르링 빈 객체들은 이제 싱글턴으로 존재하지 않는다.. new로 객체 생성한만큼 새로운 객체를 가지고있다.

# 결론

- 자바코드만 봤을땐 스프링 컨테이너의 스프링 빈이 싱글톤으로 존재할수가 없다.
- AppConfig 스프링빈의 클래스이름을 까보니 이상한 클래스임. 내가아는 클래스가아님.
- 스프링이 AppConfig클래스를 상속받은 자식클래스를 스프링빈으로 등록해놓아서 이상한 클래스가 나온것.
- 이 클래스는 싱글톤을 유지하도록 오버라이딩이 되어있기에 자바코드로는 싱글톤으로 존재할수없는 객체들이 싱글톤으로 존재하는 거였음
- `@Configuration`이 있으면 이 상속받아 생긴 새로운 클래스가 존재하게되고 스프링빈이 싱글톤으로 존재, 즉 스프링 컨테이너가 싱글턴 컨테이너 역할을 제대로 행한다.
- `@Configuration`이 없으면 요상한 클래스를 만들지 않고 우리가 만든 AppConfig클래스로 존재하게되고 이 클래스가 스프링빈으로 등록되고 new를 통해 객체가 생성된만큼 다른 객체가 존재하게된다. -> 더이상 스프링컨테이너는 싱글턴 컨테이너가 아닌것이다.
- 스프링 컨테이너가 싱글톤 컨테이너로써 스프링빈 객체를 싱글톤으로 존재하게 하는 빛나는 장점을 살리려면 `@Configuration`애너테이션을 붙이도록하자.!
