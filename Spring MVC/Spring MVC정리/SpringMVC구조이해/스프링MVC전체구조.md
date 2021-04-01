# 우리가 V5까지 어뎁터를 통해서 유연한 컨트롤러를 사용할수있는 MVC프레임워크를만들었다.

# Spring MVC구조가 위랑 똑같다.

# V5과정

1. 요청받음
2. handlerMaapping정보에서 요청온 url과 매핑되는 handler반환
3. handler에 매핑되는 handlerAdapter반환.
4. adapter.handler()로 실제 핸들러(컨트롤러) 호출
5. 리턴타입이 ModelView(데이터를 담는 모델 객체와, view가 위치하는 논리이름 반환)를 반환.
6. 컨트롤러(핸들러)로부터반환받은 modelView로부터 viewName을 viewresolver메서드를 통해서 실제 물리적인 viewName을 얻으면 반환하는건 MyView객체
7. MyView의 render메서드를 호출해서 JSP포워딩
8. HTML 응답.

이러한 과정이 Spring MVC에서는 handlerMappingMap이라던지 viewResolver메서드라던지 이런것들이 인터페이스화 되어있고 전체적인 구조는 똑같다

# SpringMVC 구조및 과정

1. 요청받음
2. DispatcherServlet(프론트 컨트롤러 서블릿 역할)이 요청을 받아서 핸들러를 조회(요청된 URL과 매핑되는 핸들러를 조회한다.)
3. 해당 핸들러에 매핑되는 핸들러 어댑터를 조회한다.
4. 핸들러 어댑터를 통해서 handle 메서드로 실제 핸들러를 호출한다.
5. 실제 핸들러가아닌 HandlerAdapter가 ModelAndView를 컨트롤러가 리턴하지않아도 ModelAndView를 만들어서 DispatcherServlet으로 반환함.즉 HandlerAdapter.handle(handler)부분에서 ModelAndView(데이터를 담는 모델과, 뷰의 논리적인 이름이잇는 viewName을 가지고있음.)
6. Dispatcher Servlet은 ModelAndView를 통해서 논리적인 viewName을 viewResolver를 통해 물리적인 viewName으로 변환하며 View를 리턴한다.(스프링MVC는 이러한 ViewResolver가 전부 인터페이스로 구현되어있음)
7. View를 통해서 render메서드를 실행하여 랜더링한다.
8. 랜더링 결과 응답

- 이렇게 Spring MVC도 프론트 컨트롤러패턴으로 구현되어있다.
- Spring MVC에서 프론트 컨트롤러 서블릿은 `DispatcherServlet`이다.그리고 이 `DispatcherServlet`이 스프링 MVC의 핵심이다.
  (공통의 기능을 처리하는 프론트 컨트롤러 서블릿.)
- 이 DispatcherServlet도 HttpServlet을 상속받아서 서블릿으로 동작한다.
- 참고로 스프링부트는 이 DispatcherServlet을 서블릿으로 자동등록. 하며 모든 경로에 대해서 이 서블릿 (DispatcherServlet)이 호출되도록 매핑한다.

- DispatcherServlet클래스의 doDispatch 메서드의 호출로 시작되고 이 메서드에 기본구조의 동작이 적혀있음.

# DispatcherServlet통한 custom 확장

- DispatcherServlet 코드를 직접적으로 변경하는것 없이 원하는 기능을 변경하거나 확장할수있다는것이 스프링 MVC의 장점이다.
- 인터페이스들을 통해서 실제 구현하여 custom한 컨트롤러(핸들러)를 만들수도있다.
- 그치만 이럴 경우는 거의없다..
