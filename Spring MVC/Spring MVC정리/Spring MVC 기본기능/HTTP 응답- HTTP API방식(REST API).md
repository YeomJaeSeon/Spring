# 전에는 응답바디에 정적리소스 템플릿엔진즉, HTML 형식으로 응답하는 방법을 알아보았다. 이제는 HTTP API방식으로 응답 바디에 Text나 Json을 내려주는 방법을 알아보자.

# String 응답바디에 넣어서 응답하기

1. 전에했던 것처럼 HTtpServletResponse를 통해서 내려줄수있따. `response.setWriter().write("ok")`

2. HttpEntity는 우리는 요청 데이터 (바디에 텍스트랑 JSON) 들어올때 받을떄 사용했었따. 되게 쉽게 header랑 body를 상요할수있었따. 이 HttpEntity를 상속받는 ResponseEntiy를 리턴하면된다. 물론 HttpEntity리턴해도됨. 이 ResponseEntity를 리턴하는 것의 장점은 status code도 동시에 정할수있따는 것이다.

```
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
```

- 그럼 동적으로 무언가 status code도 다르게 응답할수있을 것이다.

3. 너무간단한 `@ResponseBody`사용하기~ 이 애너테이션사용하면 그냥 응답바디에 데이터 넣어서 내려준다. String으로 반환면된다.
   추가로 이때는 상태코드 못내려주닌까 그냥 `@ResponseStatus`에너테이션으로 상태코드 내려줄수있다.

# JSON응답바디에 넣어서응답하기

1. ResponsEntity를 통해서 바디에 json넣어서 내려주자. 객체를 리턴하면 메시지 컨버터가 알아서 json으로 바꿔준당. - 상태코드 같이 내려줄수있음 동적으로!
2. Body에 json넣고싶으면 `@ResponseBody`를 사용해서 바디에 json넣어서 내려준다. 이때 상태코드 정해주고싶으면 `@ResponseStatus`로 상태코드 지정해준다.

-> 그런데 너무귗낳아. 메서드레벨마다 하나씩 `@ResponseBody`로 응답바디에 데이터 넣어서 내려줄 컨트롤러를 지정하는게. 그래서 클래스레벨에서 (URL조합했던것처럼..) `@ResponseBody`넣어주면 하위 메서드 레벨의 컨트롤러 모두 조합이된다. 어 그럼 우리 전에 클래스레벨에서 응답바디에 데이터 내려주는 방식 많이썼는데 `@RestController`로 .. 그건머야? 아 그건 `@Controller + @ResponseBody = @RestController`이다.

- `@RestController`의 Rest는 REST API의 REST이다. 즉 API 만들때 이러한방식을 많이사용한다.
