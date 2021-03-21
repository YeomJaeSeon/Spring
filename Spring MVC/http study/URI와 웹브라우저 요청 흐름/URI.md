# URI는 로케이터(locator), 이름(name)으로 분류될수있다.

URI

1. Uniform : 리소스 식별하는 통일된 방식
2. Resource : 자원, URI로 식별할수 있는 모든것(제한이 없음)
3. Identifier : 다른 항목과 구분하는데 필요한 정보

추가로 URI중 URL과 URN은 Locator과 Name이다.
URL : 리소스가 있는 위치를 지정한것이다.
URN : 리소스에 이름을 부여한것이다.

ex) 염재선이 사는곳은 광주 (Locator)
URN : 염재선(이름 그자체)

위치는 변할수있따. 만약 이사를가면 염재선 URL은 서울, 이렇게 위치는 변할수있지만 이름은 안변함.

-> 그러나 URI중 URN은 보편화되지않았다. 그러므로 URI를 URL과 같은 의미로 생각해도 무방하다.

# URL(URI)분석

- https://www.google.com:443/search?q=hello&hl=ko
- URl은 스킴(프로토콜), 호스트명, 포트번호, 경로(path), 쿼리 스트링(쿼리파라미터)로 구분가능.

1. scheme(프로토콜) : 프로토콜구분하는데 사용. 만약 사용하는 프로토콜이 http이면 http라고적으면됨.
2. userInfo 거의안씀. 사용자 정보를 URL에 포함해서 인증할때 사용
   https://[userinfo@]www.google.com/search?q=hello&hl=ko - 요런식으로

3. host(호스트 명) : Domain name이나 ip주소가 올수있음
   https://**www.google.com**/search?q=hello&hl=ko
4. PORT : :port번호 이런식으로 부여, https 는 기본적으로 443이므로 생략가능.

5. path : 리소스의 경로이다. , 계층적으로 구조를 나타낼때 사용함
   예를들면 items/iphone12
   https://www.google.com/items/iphone12
   이런식으로 계층적 구조를 나타낼때 사용, 리소스의 구체적인 경로가되겠다.

6. query : key=value형태의 데이터이다. ?로 시작, &로 추가기능 추가가능.
   query string ,query parameter라고 부름. string은 문자열로 데이터가 전달되므로이고 parameter와 같다해서 쿼리파라미터라고함.

7. fragment : #붙은거 본적있다. ex)react공식문서 공부할때 뭐누르면 바로 페이지 중간으로 가서 보여주고.. 즉, html내부 북마크를 사용할때 사용한다. 서버에 전송되는 정보는아니다.

# 추가로 port 헷갈려서 다시정리

- IP주소만으로는 프로세스간 구분이 안되므로 PORT번호를 통해서 프로세스간 구분을할수있다.

기본적으로 0 ~ 1023은 우리가 직접사용하지말자.
http : 80
https : 443
포트번호를이용한다

만약 내 컴퓨터에서 롤을하면서 유튜브로 노래듣고잇는데 유튜브에 댓글을 달면 유튜브 서버로 데이터를 전달하는데 서버는 TCP/IP패킷의 port번호를 보고 유튜브 프로세스가 전달한걸 깨닫고 다시 유튜브 클라이언트에게 응답한다. 롤 클라한테 응답하는게아니라.

port번호는 즉, 프로세스간 구분이 가능하게해줌.(한 컴터에 여러 프로세스가 동작할수 있으닌까.)
