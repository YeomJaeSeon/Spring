# BeanFactory vs ApplicationContext

BeanFactory(인터페이스) <- ApplicationContext(인터페이스) <- AnnotationConfigApplicationContext(인터페이스 구현 클래스)

- 이게 BeanFactory와 ApplicationContext의 관계이다.

## BeanFactory

- BeanFactory는 스프링 컨테이너의 최상위 인터페이스이다.
- 스프링 빈을 관리하고 조회하는 역할을 담당한다. ex) `getBean()`
- 지금까지 우리가 사용한 기능은 거의다 BeanFactory가 제공해주는 빈을 관리하고 조회하는 기능을 사용한 것이다.

## ApplicationContext

- BeanFactory의 모든 기능을 상속받아서 제공한다.
- 그럼 왜 굳이 ApplicationContext를 사용한거고? 상속으로 구분했어?
- 실제 어플개발할땐 빈 관리나 조회는 물론 수많은 부가기능이 필요하기 때문에 ApplicationContext를 사용한다, 이게 무슨말이냐면 ApplicationContext는 BeanFactory만 상속받는게아닌 MessageSource, EnvironmentCapable등 다앙햔 인터페이스를 상속받기 때문에 ApplicationContext를 통해서 모든 부모 인터페이스의 기능을 사용이 가능하다. 그래서 BeanFactory보단 ApplicationContext를 상요한다.

# 정리

- ApplicationContext는 BeanFactory의 기능을 상속받는다.
- ApplicationContext는 빈 관리기능 + 편리한 부가기능을 제공한다.
- BeanFactory를 직접 사용할 일은 없다. 우리는 부가기능이 포함된 ApplicationContext를 사용하면된다.
- BeanFactory나 ApplicationContext를 **스프링 컨테이너**라고 한다.
