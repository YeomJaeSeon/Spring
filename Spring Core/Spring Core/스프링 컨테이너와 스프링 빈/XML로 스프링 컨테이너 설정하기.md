# 지금까진 애너테이션 기반 자바 코드 클래스로 스프링 컨테이너를 설정했다. 스프링은 다양한 형식의 설정을 지원하는데 XML도 가능하다. 그래서 이번엔 XML로 스프링 컨테이너를 설정해보자.

- 스프링은 다양한 형식으로 스프링 컨테이너를 설정하는걸 지원한다. 요즘엔 스프링 부트가나와서 XML로 잘설정하진 않지만 그래도 한번 XMl로 설정해보고 가자.

- 애너테이셔 기반 자바 클래스로 설정할땐 ApplicationContext인터페이스의 구현클래스는 `AnnotationConfigApplicationContext`였는데 XML로 설정할땐 `GenericXmlApplicationContext`이다.
- 참고로 XML로 설정할땐 컴파일 없이 설정 정보를 변경할수있는 장점도있다.

- `appConfig.xml`은 resource 폴더아래두고 `ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml")`로 스프링 컨테이너 설정정정보 넘기면서 생성해보면 애너테이션기반 자바 클래스로 설정한거랑 아애 똑같다는걸 볼수있다. 설정하는 과정자체도 매우 흡사함 . 근데 XML이라 좀더 귀찮음.

# 스프링 컨테이너생성, 스프링 빈 조회, BeanFactory, ApplicationContext, 스프링컨테이너 설정 형식 xml도가능.. 정리해보자.

- 스프링 컨테이너를 생성할땐 설정파일을 같이넘긴다. 형식은 애너테이션 기반 자바 클래스일수도있고 XML일수도있다. 그렇게되면 스프링 컨테이너의 스프링빈은 설정파일을 보고 생성된다. 스프링 빈이 생성되는 시점에 이름은 (애너테이션 기반 자바 클래스를 통해 설정할 시에.) `@Bean`메서드의 이름이되고 value는 해당 메서드가 호출되서 반환한 객체가된다. 그리고 설정을 보고 각 빈끼리의 의존관계도 설정이된다. 스프링 컨테이너에 등록된 스프링 빈은 `BeanFactory`인터페이스의 기능을 통해 `getBean`으로 조회가가능한데 타입으로도, 빈이름으로도 조회가가능하다. 타입으로 조회할시 중복의 빈이 조회된다면 예외가 발생한다. 스프링 빈 조회할때는 다형성처럼 부모타입으로 조회하면 모든 자식 타입의 스프링빈이 모두 조회된다는 사실을 기억하자 꼮!. BeanFactory 인터페이스는 스프링 컨테이너 최상위 인터페이스이고 빈 관리자 조회하는 기능이 존재한다. ApplicationContext는 BeanFactory를 상속받은 인터페이스인데 이 인터페이스도 스프링 컨테이너라고한다. 그런데 우리는 ApplicationContext만 사용하는데 그이유는 ApplicationContext인터페이스는 다양한 인터페이스의 상속을받아 더 많은 기능을 제공하기 때문이다. 그래서 ApplicationContext인터페이스를 스프링 컨테이너로 많이사용한다. (BeanFactory 인터페이스보다.) 그리고 스프링 컨테이너는 다양한 형식의 설정을 지원한다고 방금말했는데 자바 클래스로해도되고 XML로해도된다. 스프링부트가 등장하고는 애너테이션 기반 자바클래스로 많이하는데 설정 변경할때 컴파일이 필요없는 XML로 설정하는것도 한번쯤 해보는것도 나쁘지않다.
