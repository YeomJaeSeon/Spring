# 정적페이지 index.html 웹서버에 띄워보자

- resources에 static폴더에 index.html띄워봤음
- spring boot에는 톰캣이라는 서버 내장되어있어서 port 8080에 index.html뜬다.

# 동적으로 변할수 있는 페이지 추가

- 템플릿 엔진이 동작하는 방법임 (타임리프 동작하는 과정).

- controller를 이용 (웹 어플리케이션의 시작점이 컨트롤러이다.)
- spring은 컨트롤러 annotation직접 써쭤야함
- 컨트롤러는 웹어플리케이션의 진입점이다.

- @GetMapping annotation은 http 요청에 대해서 실행할 메소드이다. 우리는 /hello http요청을 get으로 할거니 @GetMapping("hello") 를 사용했음

- http요청받으면 return하는 문자열에 해당되는 `template/문자열.html`파일을 찾는다.
- template 폴더아래에는 thymeleaf 템플릿 엔진을 통해서 입력되는 데이터에 대해서 처리가 가능함. 비즈니스로직이 가능한거구만. jsx마냥
- 위 동작은 viewresolver가 하고 해당 파일을 찾으면서 model 데이터를 전달한다.
- 이제 해당 html파일을 렌더링한다.(html변환해서 웹브에 내려줌)
