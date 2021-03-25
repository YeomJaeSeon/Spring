# HTTP에서도했찌만 한번더

# HTTP 요청메시지로 클라이언트에서 서버로 데이터를 전달하는 방법

1. GET - 쿼리파라미터로

- 검색 필터등..
- 메시지바디없이 url의 쿼리파라미터로전달

2. POST - HTML Form

- content-type : application/x-www-form-urlencoded
- 메시지바디에 쿼리파라미터형식으로전달
- 회원가입, 상품주문등.

3. HTTP message body에 데이터를 직접 담아서 요청 (HTTP API)

- HTTP API에서주로사용됨, http body에 json, xml, text등을 담아서 전달
- 주로 데이터형식은 JSON
- POST PUT PATCH뭐든가능
