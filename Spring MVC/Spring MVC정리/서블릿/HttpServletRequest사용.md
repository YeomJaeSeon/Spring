# HttpServletRequest객체를 사용하면서 안에 뭐가있는지 직적보자.

1. http start line의 정보를 가져올수있는 방법

```
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("request.getProtocal() = " + request.getProtocol()); //

        System.out.println("request.getScheme() = " + request.getScheme()); //http
        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-test
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        System.out.println("request.getQueryString() = " +
                request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); //https

        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
```

2. Http 헤더를 가져올수있는방법.

```
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + " : " + request.getHeader(headerName)));
```

request.getHeaderNames()에있는걸 하나하나 다꺼내면서 가져오는거다. 그냥 Http Request message중 헤더를 가져오는것.

3. Http Header의 유용한 정보만 특정해서 사용하는법

```
 System.out.println("request.getServerName() = " +
                request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " +
                request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]");
        // 언어 조회. 우선순위 가있음.
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " +
                        locale));
        // 내가사용할 언어(우선순위 가장높은거)
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();
        // 쿠키도 http header에담김
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " +
                request.getContentType());
        // get방식은 content거의 보내느게없으므로 null임.
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                request.getCharacterEncoding());
```

- 참고로 여기서 getLocales()는 모든 사용한 언어를 조회하는 것이고 getLocale()하면 우선순위 가장높은 현재 사용하는 언어를 가져오는 것이다. 나는 ko_KR

- Content type은 null인데 postman으로 body에 데이터를 넣어서 전달하면 null이아닌데이터가 넘어옴.

4. 부가적인 네트워크 정보를 가져오는것.

```
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " +
                request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " +
                request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " +
                request.getRemotePort()); //
        System.out.println();
        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " +
                request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " +
                request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " +
                request.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
```
