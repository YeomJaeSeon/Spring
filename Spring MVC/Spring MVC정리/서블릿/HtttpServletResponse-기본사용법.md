# 응답메시지를 편하게 만들어주는 HttpServletResponse객체 사용법을 알아보자.

- status line(start line)
- header
- body
  이렇게 세부분으로나눌수있겠따.

1. status line
   `response.setStatus(200);`이런식으로 할수있지만 하드코딩되있는걸로바꾸면더좋다
   `response.setStatus(HttpServletResponse.SC_OK);` -> 200 OK

2. Header
   cache여부, content-type(body 데이터형식), 인코딩방식, 추가로 사용자지정 헤더등을 포함할수있음

```
//        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");
```

- 그러나 이렇게하는것보단 편의 메서드가제공됨

```
    private void content(HttpServletResponse response) {
        //아래코드처럼 간단하게 할수있다.
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
        //위 생략시 자동으로 body길이 계산
    }
```

- contentType과 encoding방식을 더 편하게사용가능

3. body

```
        //[message body]
        PrintWriter writer = response.getWriter();
        writer.println("ok");
```

이런식으로 바디 데이터를 넣을수있따.

4. cookie, redirect 편의메서드

- 쿠키같은경우는 객체가 제공되서 편하게 만들수있다.
  물론 response.setHeader("cookie", ~)이렇게만들수있지만
  더편하게만들수있도록 객체가 제공됨

```
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie); // addCookie에 쿠키넣으면 된다.
    }
```

redirect는 상태코드가 주어져야하고(302) 어디로갈지 목적지인 Location이 정해져야한다.

```
    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
//        response.setStatus(HttpServletResponse.SC_FOUND); //302
//        response.setHeader("Location", "/basic/hello-form.html");

        // 위두줄안하고 아래한줄코드로 끝낼수있다.
        response.sendRedirect("/basic/hello-form.html");
    }
```

reirect도 두줄을 편하게 한줄로 바꾸는 방법이 존재한다.
