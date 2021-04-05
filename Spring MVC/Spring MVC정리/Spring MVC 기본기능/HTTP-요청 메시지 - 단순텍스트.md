# 전에는 쿼리파라미터 형식으로 데이터가 넘어오는걸 조회하는방법 (@RequestParam)과 파라미터요청을 객체로 변환하는 (@ModelAttribute)를 알아보고 적용해보았다. 이번에는 파라미터 형식으로 데이터가넘어오는게 아닌 HTTP Request body에 데이터(단순 텍스트)를 어떻게 받는지 알아보자.

- 요청파라미터로 넘어오는게 아닌 바디에 데이터가 직접 넘어오기 때문에 `@RequestParam`, `@ModelAttribute`를 사용할수없다.. 위 두개는 파라미터요청시에만 사용할수있다.

- 서블릿에서 바디 데이터받은것처럼 inputStream을 이용해서할수도있다.

- 그런데 스프링은 파라미터로 InputStream Writer자체를 제공해줘서 이걸사용해도된다.

- 근데 더좋은건 HttpEntity로 바로 바디에 의 데이터를 꺼내거나할수있다.

- 근데 더 미친건 `@RequestBody`를 통해서 귀찮게 HttpEntity이런거 안적고 바로 요청온 바디의 데이터를 조회할수있다.

- 우리가 응답을 뷰를안하고 그냥 바디에 데이터 넣어서 내려주고싶을떄 클래스레벨에서 `@RestController`로 애너테이션 컨트롤러 등록했고 또 다른방법으론 메서드레벨에서 `@ResponseBody`를 적어주었는데 이게 위에서말한 `@RequestBody`와 짝꿍이되는 녀석이다. `@RequestBody`는 요청 바디의데이터를 바로 조회하게 도와주는 편리한 기능이고 `@ResponseBody`는 응답하는 바디에 바로 데이터를 넣어서 내려주는 편리한 기능이다.

# 지금까찌 한MVC 기본기능정리

1. 먼저 스프링 부트로 프로젝트 생성(Spring web, 롬복, 타임리프 라이브러리 의존.)
2. 로깅에 대해서 간단하게알아봄 logback 라이브러리로.
3. 요청 매핑(requestMapping에대해서 알아봄.)
4. HTTP - 요청의 헤더를 조회하는법을 알아봄. (@RequsetHeader)
5. HTTP - 요청 쿼리파라미터 받는법을 알아봄(@RequestPAram, @ModelAttribute)
6. HTTP - 요청 바디에 텍스트(데이터)넘어올때 조회하는법알아봄. (@RequestBody)
