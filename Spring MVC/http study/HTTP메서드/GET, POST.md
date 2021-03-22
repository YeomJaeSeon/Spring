# HTTP 메서드들(간략히)

- GET : 리소스 조회
- POST : 요청 데이터 처리, 주로 등록
- PUT : 리소스를 대체, 해당 리소스없으면 생성
- PATCH : 리소스 부분 변경
- DELETE : 리소스 삭제

* 기타 메서드들

- HEAD : GET과 동일하지만 BODY빼고 주라~
- OPTIONS : CORS할때..
- CONNECT, TRACE..

# GET

- 리소스 조회할때
- 서버에 전달하고 싶은 데이터는 쿼리스트링을 통해서전달
- 메시지 바디를 물론 사용할수있어 그런데 권장되지않아 . 왜냐면 GET메서드에 body에 데이터있는걸 지원하지않는 서버가 굉장히 많으므로 웬만하면 GET메서드 사용할때 바디에 뭐 넣지말기로 약속

GET사용할때 http request message
GET /search?q=hello HTTP/1.1 // start line
Host: www.google.com // HTTP Header
텅~
body에는 데이터없이 http request메시지를 서버에게 보낸다.

-> 서버에서 응답하는 http response message
HTTP/1.1 200 OK // status line
Content-Type:application/json... //Header

{
데이터들.. (JSON형태로옴 ) // BODY
}

- GET HTTP 메서드는 요롱롱 뭔가 서버에게 요청하지만 데이터를 보내면서 요청하는게 아니고 그냥 요청한다. (target URI와 데이터 전달은 다름)
- 그러므로 조회요청할때 주로 사용됨

# POST

- 결론부터말하면 HTTP Body에 데이터를 넣어서 요청할때 사용됨.
- 뭔가 GET처럼 조회할때만 사용된다 이런 정해진 역할이 있는게아닌 Body에 데이터를 포함하여 요청하는 것이므로 굉장히 다양하게 사용될수있음(주로 등록하는데 사용되긴하는데 프로세스 처리하는데도 사용됨.)

POST 요청할때 http request message보기~

POST /members HTTP/1.1
Content-Type:application/json // header
//GET메서드에선 body에 데이터없으므로 없엇음

{
JSON형식의데이터
}

- 메시지 외우는게 중요한게 아니다! Body에 데이터를 넣어서 요청하는것이 POST의 특징!

- 응답 메시지는 그럼어케생겻을까
  HTTP/1.1 201 Created // status-line
  Content-Type: application/json;Content~
  //Header

{
JSON데이터.. // Body
}

# POST 메서드 파고들기

## POST메서드 주로 사용되는 기능!

- HTML Form에서 입력된 정보로 회원가입, 주문등... 즉 뭔가 요청할대 데이터도 옴 이게 핵심임
- 게시판 글쓰기, 댓글쓰기 .. 이것도 요청할때 데이터가 잇다는게 중요함
- 신규 주문 생성같은 서버가 아직없는 리소스 생성 요청. 이것도 데이터를 body에 넣어서 요청하므로 가능한거지.
- 문서끝에 데이터추가. 이것도 body에 데이터 넣어서 요청하닌까 가능

> 즉 POST메서드는 뭔가 정해진 기능이없다. 그러므로 API URI를 설계할때 설계한 URI(리소스)마다 따로 정해서 사용해야함.

## POST 정리

- 새 리소스 등록!
- 요청데이터 처리
- 다른 메서드로 애매한경우~ (ex 조회요청하고싶은데 데이터를 무조건 보내야할경우. GET은 Body에 데이터를 넣는게 권장안되므로.)
- 즉 애매~하면 POST메서드를 이용하자.
