# 한계

1. 포워드중복

- 모든 컨트롤러에서 각 뷰(JSP)를 호출하려면 포워드해야하는데 포워드하는 부분이 중복된다. 메서드 extract해도되지만 이것도 호출해야하므로 중복해서 호출되므로 중복이다.

2. viewPath중복

- viewPath가 만약 변경되면 해당 view를 사용하는 컨트롤러는(뷰를 호출하는. path.) 다 변경해야한다. 100개의 컨트롤러가 공통의 path를 사용하고있으면 다 변경해야함.

3. 사용하지않는코드

- jsp로 뷰를내려주므로 response를 거의안씀.

4. **공통처리가 어려움**

- 기능이 복잡해질수록 공통 부분이 많이 생길텐데 단순히 공통기능을 메서드로 뽑으면 될거같지만 해당 메서드도 항상호출해야하므로 호출하지않으면 문제가생기므로 중복해서 호출하는것도 중복이다.

즉 공통처리가 어려운 장점이있따.
이문제를 해결하기위해!

# 해결

- 프론트 컨트롤러라는 패턴을 이용하여 컨트롤러 호출전에 모든 공통기능을 처리해보자.
- Spring MVC의 핵심도 이 프론트 컨트롤러에있다.
- 다음시간부턴 프론트 컨트롤러 패턴을 이용하여 MVC framework를 만들어보자.
