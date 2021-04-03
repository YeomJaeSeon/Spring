# Spring MVC본격적으로 시작하기(지금까진 spring MVC의 구조를 알기위한 학습과 핸들러매핑 핸들러어댑터 뷰리졸버에대한 이론만 공부했따.)

드디어!

- 스프링이 제공하는 컨트롤러는 애노테이션기반으로 동작! -> 유연, 실용적
  (과거에는 애노테이션이 없어서 스프링이 MVC구조는 좀 약했따.)

# @RequestMapping

- `@RequestMapping`은 애노테이션을 사용하는 컨트롤러이다.
- RequestMappingHandlerMapping : `@RequestMapping`으로 정의된 컨트롤러를 매핑하는 핸들러 매핑이다.
- RequestMappingHandlerAdapter : `@RequestMapping`으로 정의된 컨트롤러를 매핑하는 핸들러 어댑터이다.
- 실제적으로 `@RequestMapping`으로 정의된 컨트롤러가 실행되기까지 RequestMappingHanlderMapping에서 해당 컨트롤러를 매핑하고 해당 컨트롤러를 기반으로 RequestMappingHandlerAdapter에서 매핑 되는 어댑터를 실행하여 ModelAndView를 반환하고 뷰리졸버 -> 뷰반환 -> 렌더링 방식으로 Spring MVC는 동작한다.

- 실무에선 99.9%이러한 애노테이션 기반의 컨트롤러를 사용한다. 그래서 애노테이션 기반의 컨트롤러의 매핑 핸들러와 매핑 어댑터의 우선순위가 가장높았다!(다른 매핑 핸들러와 다른 매핑 어댑터들보다.. 사실 그래서인줄을 모르겠음.)

# @Controller와 @RequesetMapping

- `@Controller`는 컴포넌트 스캔방식으로 스프링빈으로 등록하는 방법이다.(스프링이 이걸보면 스프링 컨테이너의 스프링 빈으로 등록한다.)
- 그리고 두번째 역할이있음! 그건바로 애노테이션 기반의 컨트롤러임을 RequestMappingHandlerMapping이 인지해서 매핑된다는 것이다. 과정으로 보면 클라이언트로부터 요청이들어오면 RequestMappingHandlerMapping이 `@Controller`를 보고 매핑되는 컨트롤러가 있구나!하고 이녀석을 DIspatcherServlet(프론트 컨트롤러 서블릿)으로 반환한다.

## @Controller 2가지 !

1. 스프링이 스프링빈으로 등록(컴포넌트 스캔방식) - 자세히들어가보면 `@Component`를 사용하는중
2. RequestMappingHandlerMapping과 매핑된다!(매핑되는 컨트롤러를 찾는데 `@Controller`를 보면 이녀석을 매핑해야되겠따고 RequestMAppoingHandlerMapping이 매핑한다.)

## @RequestMapping

- 요청정보를 매핑한다!
- 해당 URL이 해당되면 `@RequestMApping`의 메서드가 호출된다. 애노테이션기반으로 동작하므로 메서드 이름은 아무거나 OK~

RequestMappingHandlerMapping은 스프링 빈중에서!!(스프링 빈으로 등록되어있는 녀석들 중에서!!!!) `@RequestMapping`또는 `@Controller`가 **클래스 레벨**(메서드에 붙으면 인식못함.)에 붙어있는 경우 매핑 정보로 인식하여 꺼내서 사용한다. 그래서 스프링빈으로만 등록되어있으면 되므로 `@Component` 컴포넌트 스캔으로 스프링 빈으로 등록하던 `@Bean`으로 스프링빈으로등록하던 상관없다. 그래도 `@Controller`로한번에 하는게 스프링빈으로 등록도되고 클래스레벨에 있으면 핸들러 매핑에 매핑되서 컨트롤러로 인식되므로 편하다.

# 결론

- 지금까지 하던 것과달리 애노테이션 기반으로 컨트롤러를 만들었다.
- 방식은 너무너무쉽다. `@Controller`와 `@RequestMapping`에노테이션을 클래스레벨에 붙이면(스프링 빈으로 등록되어져있어야함.) 핸들러매핑이 컨트롤러임을 알고 사용한다.
- 참고로 스프링빈으로 등록하는 방법은 컴포넌트 스캔과 자바코드로 등록하는 방법 두가지가있따.
- 참고로 `@RequestMapping`은 해당 URL이 요청으로 들어오면 해당 메서드를 실행한다.
