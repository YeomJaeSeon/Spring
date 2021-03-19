# HttpServletRequest역할

- Http request message를 편리하게 사용할수있도록 개발자대신에 Http request message를 파싱해서 그 결과를 `HttpServletRequest`객체네 담아서 개발자에게 제공한다.
- 즉 HttpRequestMessage를 쉽게 개발자가 사용할수 있게 도와주는 객체이며, 서블릿이 지원하는 기능이다.

- Http 요청메시지

```
POST /save HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded

username=kim&age=20
```

- Http Start LINE

1. Http 메서드
2. URL
3. 쿼리스트링
4. 스키마, 프로토콜
   이있다.

- 헤더

1. 헤더 조회 가능

- 바디

1. form 파라미터 형식으로 조회가능
2. 혹은 그냥 json같이 데이터로 직접 조회할수도있다.

# HttpServletRequest객체는 임시 저장소기능을한다.

- Http요청이 시작되고 끝날때까지 유지되는 임시 저장소기능이다.

- 저장 : `request.setAttribute(name, value)`
- 조회 : `request.getAttribute(name)`

# 세션관리기능

- 사용자가 로그인했나 안했나등을 알수있또록 하는 세션관리기능도있따.
  `request.getSession(create : true)`

# 뭐 다양한 기능이있지만 가장중요한건 HttpRequestServlet객체는 Http요청 메시지를 쉽게 사용할수있도록 도와주는 객체이므로 이 기능에대해서 깊이있게 이해하려면 HTTP 스펙이 제공하는 요청이라던지 응답 메시지 자체를 이해하는것이중요.
