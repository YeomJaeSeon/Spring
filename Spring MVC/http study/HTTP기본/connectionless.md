# 비연결성 connectionless

- 만약 클라이언트 1억개가 서버하나에 요청한다면 1시간이고 2시간이고 클라이언트와 서버의 연결을 유지해야할까?
  -> NONO!
- HTTP는 기본적으로 연결되고 요청이 처리되서 응답되면 연결을 끊는다. 이게바로 http의 특징인 connectionless
- 이렇게되면 한시간동안 수천개의 클라이언트가 요청해도 동시에 들어오는 요청은 수십개미만으로 자원을 효율적으로 관리할수있다.

# connectionelss의 한계

- 그런데 연결을 끊고 다시 연결하고이러면 TCP/IP연결 즉, TCP의 3way handshake라던지.. 이런걸 계속 다시해야한다는거잖아.. 그럼 그만큼 시간이 걸리겠네 ㅠㅠ

- 심지어 네이버같은 웹페이지 하나 요청하는거면 응답받을때 HTMl뿐아니라 css, js, 이미지.. 뭐 엄청받잖아 이거 받을때마다 연결 끊고 다시하고그래야해? 그러면 시간 더걸리겟네. 비연결성의 장점을 알겠는데 단점도 너무 명확하다.

- 맞다. 그만큼 시간이 지연된다. 그치만 HTTP의 Persistent connections인 지속연결을 통해서 어느정도 해결가능하다.

# connectionless 극복

- 요즘은 HTTP Persistent Connections인 HTTP 지속연결로 해결하고있다.
- 하나의 요청에 대해서 그에 대한 리소스 연결을 다하고 연결을 끊는것이다.

1. HTTP Persistent connections 이용 하지않을때의 connectionless

- 연결
- HTML응답
- 종료
- 연결
- JS응답
- 종료
- 연결
- CSS응답
- 종료
  ...

2. HTTP Persistent connections 이용할때의 connectionless

- 연결
- html요청 응답
- js요청 응답
- css요청 응답
- 종료

-> 시간지연을 막을수있다.

# 대용량 트래픽 한번에 몰릴때

- 수강신청시간때 엄청나게많은 요청이 한번에 몰린다. 이러면 서버 증설을 그때 막해야한다.
  그러려먼 stateless하게 로직을 짜는것이 중요하다.
  그래야 서버확장(스케일 아웃)이 가능하기 때문이다.
  이부분이 서버개발자가 어려워하는 부분임.

# 결론

- stateless하게 로직을 구성하자.
