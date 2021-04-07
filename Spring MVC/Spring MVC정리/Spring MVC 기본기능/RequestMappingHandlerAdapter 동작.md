# 컨트롤러 파라미터보면 되게 다양한게 들어갈수있다. 이렇게 다양한 녀석들이 근데 처리가 어떻게되는걸까? 비밀은 애너테이션기반 컨트롤러를 실행하는 RequestMappingHandlerAdapter에있다.

- 컨트롤러에는 엄청많은 파라미터들이있다. 이러한 파라미터를 처리하는 `ArgumentResolver`라는 녀석이있다.

- `ReqeustMappingHandlerApapter`는 `ArgumentResolver`를 통해서 파라미터에 사용되는 객체를 만들어놓고 컨트롤러를 호출한다.

- 반대로 리턴타입도 되게많은 녀석들이있는걸봤다. `@ResponseBody`로 String일수도있고 viewName String, ModelAndView..이런것들을 처리하는 `ReturnValueHandler`가있다. `ArgumentResolver`와 같이 반환값을 변환해준다.적절히

- 이러한 `ArgumentResolver`, `ReturnValueHandler`들은 인터페이스로되어있고 엄청많은 구현체들이있따.(Spring은 완전히 인터페이스로 변경 유리한 유연한 코드설계를 돕는다..!)
- 그래서 우리가 필요하면 필요한 구현체를 만들어서 확장할수도있따.!

- 그럼 HTTP 메시지 컨버터는 어디서동작해?

# HTTP 메시지 컨버터와 ArgumentResolver, ReturnValueHandler

- Http 메시지 컨버터는 요청올때는 파라미터에 `@RequestBody`, `HttpEntity`가 올때 즉, 바디에 데이터가 담겨서 요청올때도 파라미터에 저것들이 와야 처리가된다. 그래서 파라미터객체를 생성해주는 `ArgumentResolver`에서 HTTP 메시지 컨버터를 통해서 처리한다.
- 반대로 응답에서도 `@ResponseBody`거나 리턴값이 HttpEntity이면 HTTP 메시지 컨버터가 동작하는데 이도 `ReturnValueHandler`에의해서 반환값을 변환하므로 이떄 메시지 컨버터가 동작해서 처리하게된다.

즉, 요청에서는 `ArgumentResolver`에서 HttpMessage컨버터 동작, 응답에선 `ReturnValueHandler`에서 HttpMessage컨버터가 동작한다.

잠깐! HTTP 메시지 컨버터는 HTMl 형태를 바디에 넣어서 요청 응답할때가 아닌 HTTP API방식(JSON, TEXT)등이 바디에 있을떄 요청할때 바디의 값을 처리하기 쉽게 변환해주는 녀석이고 요청할땐 파라미터에 `@RequestBody`, `HttpEntity`이고 응답할땐 반환값이 `@ResponseBody`, `HttpEntity`여야 바디에 json으로 변경되던 String이 문자로 변경되던한다.
