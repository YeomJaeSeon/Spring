# 지금 우리는 Spring MVC기본기능에 대해서배우고있다. 로깅에 대해서 간단하게 알아보았고 요청매핑하는 다양한 방법들(@RequestMapping에 다양한 매개변수를 줘서..)을 적용해서 어떤 요청이들어왔을때 어떤 메서드를 호출해야하는지의 매핑을 직접해보았다. 그럼이제 HTTP요청이들어왔을때 HTTP헤더정보를 조회하는 방법을 알아보자.

- 스프링 애너테이션 기반 컨트롤러는 다양한 파라미터를 지원한다. 이를 통해서 HTTP헤더를 조회해보자.(요청들어온 http request message의.)

```
    @RequestMapping("/headers")
    public String headers(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMethod httpMethod,
            Locale locale,
            @RequestHeader MultiValueMap<String, String> headerMap,
            @RequestHeader("host") String host, //헤더 하나만 가져올수있음 이렇게.
            @CookieValue(value = "myCookie", required = false) String cookie
            ){
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}",host);
        log.info("myCookie={}", cookie);

        return null;
    }
```

- 요청매핑할땐 `@RequestMapping`에 여러 파라미터를 줘서 다양한 요청에대해서 컨트롤러를 매핑했는데 헤더는 메서드의 파라미터에 다양한 기능을 지원해서 이걸사용해서 요청온 헤더라던가 여러 정보를 알수있구나..
