# 웹 스코프

- 웹스코프는 웹 환경에서만 동작한다.
- 프로토타입 스코프 빈과는 다르게 스프링이 웹스코프 빈 소멸까지 책임진다.

# 웹 스코프 종류

1. request 스코프 : HTTP요청 **하나**가 들어오고 나갈때 까지만 유지되는 스코프이다. 각각의 HTTP 요청은 다 다른 각각의 빈 인스턴스(객체)가 생성되고 관리된다. 이말은 HTTP요청이 세개들어오면 (동시에) 세개의 다른 빈 인스턴스가 생성된다.
2. session 스코프 : HTTP session과 동일한 생명주기를 갖는다.
3. application 스코프 : 서블릿 컨텍스트와 동일한 생명주기를 갖는다.
4. websocket : 웹 소켓과 동일한 생명주기를 갖는 스코프이다.

# request 스코프 언제쓸까?

- 사용자의 요청이 에 대해서 새로운 빈 객체를 계속 생성해야할때 사용하면좋다. 예를들면 엄청나게 많은 사용자의 요청에 대해서 로그를 통해 구별할때.

# request 스코프

- HTTP요청이 요청이 하나 들어오고 나갈때까지 스프링빈의 생명이 유지된다. 그리고 각 HTTP마다 별도의 빈 객체가 생성된다.
- 컨트롤러에서 request 스코프의 빈 을 의존하고있으면(생성자 주입으로) 스프링 컨테이너가 뜰때(스프링 부트가 실행될때, 스프링부트 내부에 스프링 컨테이너가 존재함.) 생성자가 호출되는데 그때 request 스코프 빈의 의존관계를 주입받으려하지만!!! 스프링 컨테이너에 request스코프 빈이 없기 때문에 에러가뜬다.
- 왜냐면? request 스코프 빈의 빈 생명주기는 HTTP요청이 들어와야 생성되고 요청 나갈때 빈 소멸되기 때문에, 아직 HTTP 요청이 한번도 들어오지 않았으므로 스프링 빈이 없다고 에러가 뜨는 것이다...!!!
- 그렇다면 HTTP요청이 들어와야 존재하는 빈을 어떻게하면 스프링 컨테이너 생성될때 의존관계 주입되도록할까? - 스프링 컨테이너 뜰때 request 스코프 빈 의존관계 주입하는 빈에서 의도적으로 HTTP요청을 하여서 requst 스코프 빈을 생성하면 될까?
