# 서블릿 생성해서 요청메시지로부터 요청받아 response객체 만들어서 응답메시지 웹브라우저한테 내려보자.

# 스프링부트에서 서브릿 자동등록

```
@ServletComponentScan // 즉, 서블릿 자동등록
// 스프링 부트에서 서블릿쓰려면 이 애노테이션 지원해줌.
// 내 패키지 하위패키지 뒤져서 서블릿 찾아서 자동으로 서블릿을 등록해서 실행하도록 도와줌
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}
}

```

- `@ServletComponentScan` 애노테이션을 붙이면 자동으로 서블릿 등록해줌(내 하위 패키지 아래있는 녀석들의 서블릿을.)

# 서블릿 객체 생성

- 스프링부트는 WAS(톰캣)를 내장하고있다.
- 이 WAS는 서블릿 컨테이너를 생성하고 요청이들어오면 요청 객체나 응답 객체를 WAS가 만들어서 서블릿 컨테이너의 서블릿 객체한테 인자로 전달해준다.
- 이 서블릿 객체를 만들어보자.

```
// 서블릿 이름은 최대한 클래스이름과 비슷하게 한거뿐임 과몰임 하지말자.
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // 웹브라우저에서 http 요청이들어오면 Was(서블릿 컨테이너)가 response, request객체를 만들어서
    // 서블릿에 던져줌.
    @Override // 서블릿호출되면 이 service메소드 호출됨. ctrl + o로 쉽게메서드생성함.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);
        // servlet이 없엇으면 개발자가 http requeset message를 다 파싱하면서 읽어서
        // 분석해야하는데 서블릿이 쉽게 request 메시지를 분석하는 기능을 제공.

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8"); // 문자 인코딩은 왠만하면 utf-8써야함
        // 위 contenttype이라던가 encoding type은 response헤더에 들어가는것이다.
        response.getWriter().write("hello " + username); // http response body에 데이터가 들어감.

        // 서블릿을 이용해서 이렇게 쉽게 요청이나 응답에대한 처리를 할수있음. 서블릿 없었으면
        // 이거다 파싱하면,, response 메시지 다 하나씩만들고 마이 복잡...
    }
}

```

- `@WebServlet` 애노테이션을 붙여야 서블릿생성하는것이고 이 때 서블릿 객체의 이름과 mapping될 url을 적는다.(요청으로 들어올 url을 말하는것.)
- 아까도 말했듯이 웹브라우저(클라이언트)가 요청(http request message)하면 WAS(서블릿 컨테이너), 우리는 톰캣이지?, 이 톰캣(WAS)가 request,response객체를 만들어서 서블릿 객체한테 인자로 전달하게됨. 서블릿 객체는 HttpServletRequest, HttpServletResponse객체로 전달받게되는것.
- 서블릿객체는 쉽게 http request message를 파싱해서 분석해 개발자가 사용하기 쉽게 도와주고, http response message도 쉽게 만들수 있도록 도와준다.

- 그리고 service메서드는 서블릿 호출하면(서블릿 컨테이너, WAS, 톰캣이 호출) 자동으로 호출된다.

# request들어온 쿼리 파라미터 읽기.

- HttpServletRequest를 통해서 쉽게 요청들어온 쿼리파라미터 읽을수있음

```
        String username = request.getParameter("username");
        System.out.println("username = " + username);
```

- 완전쉽다. 서블릿없었으면 많이 복잡했을 부분임. (http request message전부다 분석해야하므로)

# 서블릿을 통한 http response message만들기(쉽게 ^^)

```
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8"); // 문자 인코딩은 왠만하면 utf-8써야함
        // 위 contenttype이라던가 encoding type은 response헤더에 들어가는것이다.
        response.getWriter().write("hello " + username); // http response body에 데이터가 들어감.
```

- setContentType이랑 setCharacterEncoding은 http response message header에 넣을 정보이다. (참고로 인코딩 EUC말고 요즘엔 거의 utf-8이용)
- getWriter().write()같은 경우는 http response body에 들어갈 정보이다.

- http response message를 만들어서 응답하는것 역시 서블릿을 이용하면 되게 편함.(전에는 response message하나씩 다만들어야했어. ㅠㅠ)

# 팁

- 웹브한테 요청들어올때마다 HTTP request message 출력하기 : `logging.level.org.apache.coyote.http11=debug` 이문장 application.properties에 입력하면 됨.
- 그럼 http요청 다양한 정보들을 출력할수있음
- 물론 운영서버말고 개발서버에서만 하자. (당연한것 .)

# 서블릿 컨테이너 동작방식

## 내장 톰캣 서버 생성(WAS)

1. 스프링부트 실행
2. 스프링부트가 내장 톰캣 서버 생성
3. 톰캣(WAS)이 서블릿 컨테이너 생성
4. 서블릿 컨테이너가 서블릿 객체 생성 (서블릿 컨테이너는 서블릿 객체 라이프사이클 관리함.)

## 웹브로부터 http request message를 받음(요청이들어옴)

1. WAS(톰캣)이 request, response객체만듬
2. 스프링 컨테이너(WAS)가 request, response객체를 서블릿 객체 인자로 전달.
3. 서블릿 객체 호출, 즉 service메소드 호출
4. 서블릿 객체 호출이 종료되면 response객체를 통해서 http response message를 생성해서 웹브라우저에게 내려줌. (우리는 http response body에 데이터만 넣어서 내려줬기 떄문에 페이지 소스보면 text 데이터 딸랑하나 존재함.)
