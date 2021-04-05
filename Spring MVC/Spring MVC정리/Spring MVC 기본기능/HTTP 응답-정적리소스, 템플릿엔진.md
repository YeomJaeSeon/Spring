# 지금까지는 요청중심으로 어떻게 애너테이션 기반 스프링 MVC 컨트롤러가 매핑되서 사용되는가를 집중적으로봤는데 이제부턴 응답에대해서 볼것이다.

기본적으로 서버에서 웹브라우저로 내려주는 데이터의 형식은 세가지이다.(웹브라우저에서 웹서버도 세가지였지? GET 쿼리파라미터, POST HtmlForm, HTTP API(바디에 text, json..))

1. 정적리소스 내려주기
2. 동적인 HTML(서버에서 로직처리해서 내려주는) 즉, 뷰 템플릿, 템플릿 엔진 ex)JSP, thymeleaf
3. HTTP API방식(응답 바디에 text, json 넣어서 내려주기)

- 이번에는 정적리소스와 템플릿엔진을 서버에서 어떻게 내려주는지 알아보자.

## 정적리소스 내려주기

- 너무간단하다. 스프링 부트는 정적 리소스 경로를 이미 정해놔서 거기다가 정적리소스를 만들면 된다. ex) /static, /public, /resources/ ..

## 뷰템플릿 내려주기

뷰 템플릿을 거쳐서 HTML이 생성되고, 뷰가 응답을 만들어서 전달한다. 일반적으로 HTML을 동적으로 생성하는 용도로 사용하지만. 다른 것들도 가능하다.

- 스프링부트는 기본 뷰 템플릿 경로를 제공한다.

`src/main/resources/templates` (뷰 템플릿 경로)

### 컨트롤러 String반환하는경우

- `@ResponseBody`없으면 논리적인 viewName을 반환해서 ModelAndView로 변환되고 뷰리졸버로 뷰 리턴해서 뷰를통해 랜더링된다. 즉 뷰를랜더링함.
  (논리이름이 `response/hello`이면 뷰리졸버에의해 변하는 물리적인 이름은 `templates/response/hello.html`이다.)

- `@ResponseBody`있으면 응답 바디에 데이터 넣어서 그대로 내려줌.

### void반환하기- 추천X

- @Controller를 사용하고 HttpServletResponse, OutputStream(Writer)같은 HTTP 메시지 바디를 처리하는 파라미터가없으면 참고해서 논리이름으로 알아서 반환된다. 즉, String으로반환하지않고 void로 반환해도 뷰의 논리이름 viewName을 알아서 인식하고 뷰랜더링해준다. 그러나 가독성이 너무안좋음! 탈락!

### HTTP메시지

- `@ResponseBody`, `HttpEntity`를 사용하면 뷰 템플릿을 사용하는것이 아니라, HTTP Body에 데이터넣어서 내려준다.

## Thymeleaf 스트링부트설정

- build.gradle보면 타임리프 라이브러리가 설정되어잇다. 이를 토대로 스프링부트는 prefix와 suffix를 default로 설정하고있다. 그래서 `application.properties`에 해당설정이없더라도 알아서 스프링부트가 디폴트로 값을 가지고있다 로깅에서 info를 디폴트로 로그레벨설정하는것처럼.
