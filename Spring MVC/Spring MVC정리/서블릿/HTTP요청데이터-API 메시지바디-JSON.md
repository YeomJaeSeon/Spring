# 전시간엔 http 요청데이터를 바디에넣어서 보냈는데 단순히 텍스트였따 이번엔 JSOn을 바디에 넣어서 요청해보자.

- JSON은 기본적으로 바로 받기보단 객체로 바꾸어서 주로 받는다.
- 물론 JSON도 문자이고 inputStream은 바디를 그대로 읽어오기 때문에 그렇게해도 읽어올순잇지만 json답게읽어와봦.
- spring boot는 기본적으로 jackson라이브러리를 가지고있고 이를 이용해서 json을 객체로 파싱할수있다.

- 추가로 json을 객체로 바꾸는 클래스의 getter setter는 lombok으로 쉽게 생성가능하다.

`private ObjectMapper objectMapper = new ObjectMapper(); // spring boot가 기본으로 가지고잇음` 로 일단 json을 객체로파싱할수있게 인스턴스만들고

`HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);` 를 통해서 helloData객체를 생성한다. 이는 json을 객체로 파싱한 결과이다.

- 이렇게 HTTP Body에 json 데이터를 담아서 요청할때 서블릿에서 request객체를 이용해서 어떻게 받을수있는지 알아보았다.

- 추가로 우리 HTML Form의 post방식으로 데이터를 요청받을 때 request.getParamter로 받앗는데 inputstream으로 가능하다~ 왜냐면 HTML Form의 POST방식도 HTTP Body에 데이터가 담겨서오닌까 inputStream가능해.

# 결론

- JSON을 HTTP 바디에 담아서 요청하는 HTTP Message는 서블릿에서 inputstream으로 받는건 동일하나(HTTP Body에 담겨서오는 데이터이므로) json을 객체로 파싱하느과정이 필요하고 이는 spring boot에서 기본적으로가지고있는 라이브러리인 jackson을 이용해서 파싱한다.
