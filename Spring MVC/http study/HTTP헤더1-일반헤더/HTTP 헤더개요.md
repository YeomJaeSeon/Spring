# HTTP 헤더

- HTTP 헤더는 HTTP 전송에 필요한 모든 부가적인 정보가들어있따. ... 굉장히많아.

## 과거(RFC2616)

- 헤더가 분류되어있었음 General 헤더(메시지 전체에 적용되는 정보), Request 헤더(요청 정보) Response 헤더(응답정보), Entity헤더(엔티티 바디정보) ...

- 그래서 메시지 바디는 엔티티 를 전달하는데 사용되었다.
- 엔티티 바디는 응답이나 요청에서의 실제 전달되는 데이터이고 엔티티 헤더는 엔티티 바디의 데이터를 해석할수있는 다양한 정보를 제공했다.

그러나.. HTTP 표준이 변경 RFC2616->RFC7230~7235

그러면서 Entity -> Representation(표현)
으로 변경되었다.

`Representation = representation Metadata + Representation Data`

## 최신(RFC7230)

- 메시지 바디를 통해 표현데이터 전달된다.
- 메시지 바디 = 페이로드
- 표현은 요청이나 응답에서 전달하는 실제 데이터이다.
- 표현 헤더는 표현데이터를 해석할수있는 다양한 정보를 제공.

- 그닌까 뭐? HTTP표준이변경되면서 HTTP 바디에 담기는 데이터를 지칭하는 이름이 Entity -> Representation으로 변경

왜?

- 서버에서 DB에서 데이터를 JSON으로 내려줄수도, HTML에 데이터동적으로 넣어서 내려줄수도있따. 이는 데이터베이스의 데이터를 JSON으로 표현할수도, HTML로 표현할수도 있다는의미! 그래서 HTTP바디에 넣어져서 오는 데이터를 표현데이터라고한다. 그에 맞게 해당 표현데이터에 대한 다양한 정보를 표현 헤더에서 제공한다.

- 여기서말하는 표현(Representation)의 R이 그 자주듣는 Rest API의 R이다.
