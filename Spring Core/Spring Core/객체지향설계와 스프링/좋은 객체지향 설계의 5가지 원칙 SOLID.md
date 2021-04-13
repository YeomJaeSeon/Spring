# 좋은 객체지향 설계의 5가지원칙 - SOLID

(로버트 마틴이 5원칙 정리.)

1. SRP - 단일책임원칙
   한클래스는 하나의 책임만 가져야한다. 사실 실제로 한책임을 가지긴어려우나 그래도 중요한건 변경이일어났을 때 파급효과가 적어야한다는 의미이다. 하나의 변경이일어났을 떄 뭐도바꾸고 뭐도바꾸고.. 이런 변경의 파급이 크면 단일책임의 원칙을 지키지못한것.

2. OCP - 개방폐쇄 원칙
   확장에는 열려있으나 변경에는 닫혀있다. 다형성을 생각해보면 MemberRepository라는 인터페이스를 통해(역할을 통해) 구현인 MemoryMemberRepository클래스와 JDBCMemberRepository를 쉽게 갈아 끼웠다. (다형성을 통한 변경에 유리한 코드설계)그러나 MemberService는 (MemoryMemberRepository와 JDBCMemberReository인 구현을 실제로 사용하는 클라이언트부분)은 실제로 변경이 일어난다.

(MemberRepository는 인터페이스고 MemberMemberRepository, JDBCMemberRepository는 해당 인터페이스 구현한 구현체이다.)

```
//MemberService

private MemberRepository memberRepository = new MemoryMemberRepository();
```

-> 구현체를 JDBCMemberRepository로 갈아끼운다면

```
//MemberService

private MemberRepository memberRepository = new JDBCMemberRepository();
```

- 이렇게 실제로 클라이언트 부분 (MemberService)에서 실제로 변경이일어난다. 이게 OCP의 한계이다.이렇게 구현객체를 변경하려면 클라이언트 코드를 변경해야만한다.**다형성을 이용해도 OCP**를 지킬수가 없다..

-> 무언가 더필요하다...

3. LSP - 리스코프 치환원칙
   인터페이스의 약속을 지켜야한다는것이다. 예를들면 move(앞으로 이동으로 약속)라는 추상메서드를 구현하는 구현체는 move를 뒤로 이동으로 변경했다. 이건 LSP를 위반했다. LSP 리스코프 치환원칙을 지켜야 구현체는 해당 인터페이스를 믿고 구현을 할수있다.

4. ISP - 인터페이스 분리 원칙
   인터페이스를 하나의 몽뚱이로 가지고있는것보단 세세하게 기능에따라 인터페이스를 분리하는 것이 좋다는 원칙이다. 자명하다. 그렇게되면 인터페이스도 명확해지고 대체 가능성도 높아진다.

5. DIP - 의존관계 역전원칙
   추상화에 의존해야지 구체화에 의존하면 안된다는 원칙이다. 무슨의미냐면 역할에 의존해야지 구현에 의존하면 안된다는뜻이다. 즉, 인터페이스에 의존해야지 구현체에 의존하면 안된다는 뜻이다. 추상화에 의존하징낳고 구체화에 의존하게되면 변경이 너무힘들다. 그런데 다형성을 이용하면 이 원칙이 지켜질까?
   -> 그렇지않다. OCP와 매우 비슷하나 코드를 본다면

(MemberRepository는 인터페이스고 MemberMemberRepository, JDBCMemberRepository는 해당 인터페이스 구현한 구현체이다.)

```
//MemberService

private MemberRepository memberRepository = new MemoryMemberRepository();
```

-> 구현체를 JDBCMemberRepository로 갈아끼운다면

```
//MemberService

private MemberRepository memberRepository = new JDBCMemberRepository();
```

- 위 코드 상황에선 다형성을 이용했는데. 변경에 유리한 객체지향 설계를 했는데도 DIP를지키지못했다. 왜냐면 클라이언트 코드에서 추상화에 의존해야하는데 구체화에도 의존했기떄문.(구현체인 MemoryMemberRepository, JDBCMemberRespository 를 MemberService인 클라이언트 코드에서 의존하고있다. 즉 사용하고있다.즉 코드를 알고있따..)

-> OCP처럼 DIP원칙지키려면 뭔가가 더필요하다. 다형성만으로는 안된다..

# 정리

- 객체지향의 핵심은 다형성! -> 변경에유리한 유연한 코드설계가가능. 껍데기, 알맹이,,, 역할 구현,,, 선언 구현으로 분리하여서. 알맹이 구현, 을 쉽게 변경할수있다는점..!
- 그러나 이런 다형성만으론 쉽게 변경을 할수없다. 뭔가 부족하다, 한계가 존재한다.
- 다형성 만으로는 클라이언트 코드도 함께 변경된다. (A, B클래스 예제에서 메인메서드에선 결국 사용할 객체를 변경하는 것과같음..)
- 다형성만으론 DIP, OCP를 지킬수없다.(OCP 못지킨것 : 클라이언트코드에서 변경이일어남. , DIP : 클라이언트코드에서 구현체의 코드를 알고있음.)
- 뭔가가 더필요하다.라는 사실을 여기선 느끼면 OK!!
