# 텍스트 , HTML말고 응답데이터를 JSON으로 뿌리는 HTTP API에 대해서 알아보자.

- json을 저장하는 객체를 이용해서 json데이터를 만들고 jackson라이브러리를 이용해서 객체를 문자로 바꿔서 response body에 데이터넣어서 내려주면 끝임
  개쉬움

- contenttype이 json이므로 `application/json`이라고해주고 인코딩 형식지정하고
- 인스턴스를 이용해서 json에들어갈 데이터지정하고
- body에는 객체를 못내려주닌가 문자열(json)을 내려주면 끝임(jackson라이브러리를 이용해서..)

```
    HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);
```

객체로 json데이터 만들고

```
        String result = objectMapper.writeValueAsString(helloData);
        // 객체를 문자로바꿔준다.
        PrintWriter writer = response.getWriter();
        writer.println(result);
```

- 객체를 objectMapper인 jackson으로 문자열로 바꿔서 response body에 넣어서 내려준다.
