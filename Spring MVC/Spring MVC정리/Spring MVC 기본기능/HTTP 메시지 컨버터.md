# 웹브라우저에서 요청이올때 쿼리파라미터가아닌 바디에 데이터가 담겨서요청이오거나 응답을 할때 HTML을 바디에 넣어서응답하는게아닌 string이나 json으로 HTTP API방식으로응답할때 즉, HTTP API(REST API)방식으로 요청, 응답할때 데이터를 쉽게 사용하도록 변환해주는 HTTP Message Converter를 이용하면좋다.! Spring MVC에서 제공해준다.

## 메시지 컨버터가 동작할 때

- 요청 : 파라미터에 `@RequestBody`나 `HttpEntity혹은 그 자식인 RequestEntity`를 사용할때
- 응답 : `@ResponseBody`애너테이션이 적용되어있거나 리턴값이 `HttpEntity혹은 그자식 인 ResponseEntity`일 경우

-> HTTP API방식으로 바디에 String이나 JSON데이터를 콱넣고 요청받을때나 응답할때 메시지 컨버터가 동작한다.

## 메시지컨버터

- 메시지 컨버터는 interface로되어있음. 다양한 구현체들의 껍데기역할을한다.(StringHttpMessageConverter, MappingJackson2HttpMessageConverter...)
- 구현체인 컨버터들은 HandlerMapping마냥 우선순위가있다. byte-> String -> Jackson2..
- 메시지 컨버터는 `@RequestBody HttpEntity @ResponseBody`등을 보고 메시지 컨버터가 동작하지만 그이후의 동작은 클래스 타입확인 -> 미디어 타입까지 확인하고 해당 메시지 컨버터 구현체를 쓸지 정한다. 클래스타입과 미디어타입이 적절하지않으면 다음 우선순위의 구현체를 다시 검사함.
- 아무튼 이런식으로 자신에 맞는 컨버터를 찾고 요청이면 적절히 파라미터에 값을 변환해서 넣어주고 응답이면 적절히 값을 json으로 바꾸든해서 응답 바디에넣어서 응답해준다.

## 요청 데이터 읽을 때의 메시지 컨버터 동작

1. HTTP 요청
2. 파라미터에 `@RequestBody`나 `HttpEntity`가 있어! -> 메시지컨버터 동작
3. 클래스타입 확인
4. 미디어타입(content-type)확인
5. read()로 객체 생성해서 파라미터에 넣어줌

## 응답 할때 메시지 컨버터 동작

1. 컨트롤러에서 `@ResponseBody`가 적용되어있거나 리턴값이 `HttpEntity`이다. -> 메시지 컨버터 동작
2. 클래스 타입확인
3. Accept확인(클라이언트의 헤더) - Accept헤더는 클라이언트가 받을수있는 형태를 나타낸것임 content-type과는다르다. content-type은 바디의 데이터 형태
4. write()로 응답 바디에 json으로 바꾸든 string으로 바꾸든 선택된 메시지 컨버터 구현체로 동작하고 응답한다.

# 결론

- 메시지 컨버터는 HTTP API방식(바디에 데이터를 넣고요청받을 때나 바디에 데이터를 넣고 응답할 때)에서 쉽게 바디의 데이터를 사용하기 좋게 변환해주는 Spring MVC가 제공해주는 녀석이다.
- 그러므로 `@RequestBody` `HttpEntity` `@ResponseBody`를 보면 메시지 컨버터가 동작한다.
- viewName을 반환해서 뷰렌더링할대 사용되는 녀석이아니고!!! HTTP API방식에서 사용되는 녀석이다.
