# 새로운 정책을 도입

- 우리는 객체지향설계를 너무잘해놨다. 다형성, 인터페이스로 변경에 유리한 유연한 설계를 해놨다. 왜냐면 미리 이게 변경이될줄 알았으닌까(기획자가 언질해줬음)

- 그런데 실제로 새로운 정책을 도입해서 구현체를 바꾸려고하니 클라이언트 코드를 변경해야하네 왜지? 하고 보닌까 이런! 구체화에 의존하고있네.. DIP도 지키지못했고, 그러다보니 OCP도당연하게 지키지 못하는 상황이다.

- 일단 해결하려면 추상화에만 의존해야하는데. 그러면 누군가가 구현체를 연결해줘야하잖아. 그역할을 만들면되겠네!!

# 관심사의 분리

- 지금 클라이언트코드는 너무다양한 책임을 가지고있어. 객체를 생성하고 연결관계도 클라이언트코드가 맺어주네

- 관심사를 분리하자. 분업화하자! 단순히 클라이언트코드는 코드 실행만해라. 객체를 생성해주고 어플 전체의 구조를 설정하는 부분은 외부에서 하는걸로 분리하자.

- AppConfig클래스를 만들어서 객체를 만들고 해당 객체 생성자에 구현체를 주입해줬다.

- 클라이언트코드는 단순히 생성자로 구현체의 의존관계를 주입만 받으면된다. (인터페이스 다형성이기에 가능한것. 모르면 자바공부 부족한것.)

- 이러니 완전 관심사가 분리되었고 하나의 책임만하고있다. 클라이언트코드가..
- 그런데 그것보다도중요한건 이전에 가지고있던 구체화에 의존하고있는 DIP도 해결했네!?

# AppConfig 리팩토링

- 중복되는 코드도 많거니와 구현과 역할이 분리가 잘되어있지않다.
- 역할(인터페이스를 참조변수 타입으로)과 구현(을 리턴)을 분리하였다.

- 그러니 AppConfig는 어플전체의 구성을 한눈에 보여주게되었다!!

# AppConfig로 관심사 분리이후 새로운 정책도입

- 당연하게도 구현체의 의존관계는 관계설정하는 역할을 담당하는(그부분을 책임지는)AppConfig에서 행하면되고 새로운정책을 도입했다.

- 클라이언트코드는 하나~~~~~~~~도 변경하지않았는데 어플이 잘돌아간다.

- 이로써 클라이언트코드의 문제점인DIP OCP도 지키면서 관심사도 완전 잘분리되었다..!
