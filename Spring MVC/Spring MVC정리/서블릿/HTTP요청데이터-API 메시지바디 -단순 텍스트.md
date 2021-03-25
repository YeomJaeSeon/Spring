# 이번엔 HTTP body에 데이터를 넣어서 서버에 요청해보자.(Http message가 전달되는거겠찌. 거기 Body에 텍스트 데이터가 같이 전달되는 상황이다.)

- 간단하게 텍스트부터해보자.String.

- `request.getInputStream()`이렇게 byte단위로 body의 데이터를 받는다.
- byte단위로 받으면 string으로 변경해야겠다
  `String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// byte를 문자로바꿀땐`
  이런식으로 string으로 변경하고 byte단위에서 string으로 변경할땐 인코딩 형식을 적어준다. 역도 그러하다.
- test는 포스트만으로 테스트한다.

# 결론

- 서블릿에서는 request객체가 `InputStream`으로 Http message의 Http body데이터를 읽을수있다.
- 쿼리형식의 데이터는 `request.getParamter()`로 읽을수있었쯰? 이제부턴 Http Body에 담겨오는 데이터를 서블릿 객체에서 읽어볼것이다.(물론 HTML Form형식도 쿼리파라미터형식으로 Body에 데이터가 담겨서 오긴한다.)
