# HTTP - HyperTextTransferProtocol

- 말그대로 초창기에는 HTML만 전송가능
- 지금은 모든것을 HTTP 메시지에담아서전송가능(정확히는 body에)

- 지금은 HTTP시대

# HTTP Message 구조

- startline
- http header
- CRLF(공백라인)
- http body

# 시작라인

- startline = request-line(요청 메시지에선)
  / status - line(응답 메시지에서)

- start line(request-line)
- HTTP메서드(GET, POST, PUT, DELETE..)
- 요청 대상(/search?q=hello) - 절대경로 + 쿼리스트링
- HTTP Version (ex ) HTTP/1.1)

- startline = status-line(응답메시지 start line)

- start-line(status-line)
- HTTP버전(spec) ex) HTTP/1.1
- HTTP 상태코드(status code) - 요청 성공, 실패를 나타냄
  200 : 성공
  400 : 클라이언트 요청오류
  500 : 서버 내부 오류
- 이유문구(사람이 알아먹기쉽게 )

# HTTP 헤더

- Host: www.google.com
- Content-type: text/html;charset=UTF-8
  이런식으로 header-field = field-name: OWS ~
  (OWS : 띄어쓰기 허용)

- HTTP헤더의 용도는 HTTP전송에 필요한 모든 부가정보들이들어있음(뭐 body길이, 압축 여부, 인증, ...)

- 필요시 임의의 HTTP헤더를 추가할수있긴한데. 이부분은 클라이언트랑 약속이 되어있어야겠따.

# HTTP 바디

- 실제 전송할 데이터
- HTML뿐만아니라 이미지, 영상, JSON등 byte로 표현할수있는 모든 데이터를 전송할수 있다.

# HTTP 메시지를 보면 알수있듯이 단순하고 확장가능하다!

- HTTP는 단순하다. 스펙도 읽어볼만함 ㅋㅋ
- HTTP 메시지는 매우단순(startline- HTTP헤더- HTTP body)
