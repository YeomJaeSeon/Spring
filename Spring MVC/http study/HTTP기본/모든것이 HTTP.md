# HTTP

- HyperTextTransferProtocol

-> 처음에는 HyperText(링크)를 통해 이동하는 HTML과 텍스트만 전송하는 프로토콜이였음
요새는
모든것이 HTTP를 통해서 전송된다.
= 모든것이 HTTP메시지에 담겨서 전송된다.

- HTML, TEXT, 이미지, 음성, JSON...

# HTTP역사

HTTP/0.9 .. -> **HTTP/1.1** : 요새 가장많이 사용된다. 우리에게 가장중요함
왜? HTTP/2나 HTTP/3 도 많이 사용되지만 HTTP/1.1 SPEC에서 성능만 향상된것이므로 HTTP/1.1을 확실히아는게 중요
참고로 HTTP/1.1 에도 여러 버전이나누어져있음(RFC2068, RFC2616 ...)

# 기반프로토콜

-> OSI 7게층에서 TCP, UDP는 Transport계층이다.

- TCP(Transmission Control Protocol) 전송제어 프로토콜 : HTTP/1.1, HTTP/2의 기반 전송프로토콜임
- UDP(User Datagram Protocol) : HTTP/2의 기반 전송프로토콜임.

-> 요새는 HTTP/2, HTTP/3도 점점 사용이 증가한느 추세이다.

# HTTP특징

1. 클라이언트 서버구조
2. stateless, connectionless
3. HTTP 메시지
4. 단순함, 확장가능
