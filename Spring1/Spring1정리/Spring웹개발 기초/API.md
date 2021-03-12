# 지금까지

지금까진 정적컨텐츠 제외하고 MVC, 템플릿엔진으로 MVC모델쪼개서 서버에서 제공하는 데이터에맞게 html변환해서 클라이언트에게 제공했다.

API방식은?

# API방식

## @ResponseBody 문자반환

웹브한테 요청이들어오면 톰캣웹서버가 받아서 톰캣 웹서버는 스프링에게 요청들어온 걸 보내준다. spring에서 컨트롤러중 매핑된 메소드가 있는지 본다.
어? 그런데 @ResponseBody가있네 그럼 데이터를 **HTTP response body**에넣어서 클라이언트에 전달함.

기존 MVC 템플릿엔진에선 html로 변환해서 전달했는데 좀 다르다..

즉, api방식은 뷰가없네? 타임리프같은 뷰가없이 그냥 데이터만 전달하는구나..

## @ResponseBody 객체반환

위에선 문자를 반환했지만 @ResponseBody를 이용해서 객체를 반환하면?
결론: JSON으로 데이터를 클라이언트에게 전달.

1. 웹브가 요청
2. 내장 톰캣 웹서버가 스프링에게 전달
3. 컨트롤러 매핑된 녀석이있어 그런데 `@ResponseBody??`
4. 그럼 MVC, 템플릿엔진때완 다르게 viewresolver가 아닌 HttpMessageConverter가 동작.
5. 해당 메소드가 반환하는값이 문자면 StringConverter가, 객체면 JsonConverter가 작동해서 JSON으로 변환해서 요청한 웹브한테 데이터 전달..
