# MVC패턴에 대해서알았으니 뷰 기능특화된 JSP와 자바코드 특화된 서블릿으로 MVC패턴 만들어서 적용해보자.

- view는 JSP로
- controller는 servlet으로..

무조건 MVC패턴에선 뷰로 바로못가고 컨트롤러를 거쳐야한다.
그래서 컨트롤러인 servlet에서 뷰를 호출해야하고 모델에 데이터를 넣기까지 해야한다.(뷰는 모델에있는 데이터를 참조해서 랜더링에만 집중가능..)

- /WEB-INF 경로는 직접 JSP, 즉 뷰로 URL로 요청할수없다. MVC패턴에선 컨트롤러를 무조건 거쳐야 뷰로갈수있는게 의미가있으므로 이러한 약속이 존재한다. 그래서 jsp만으로 웹어플리케이션만들때완다르게 직접 jsp를 url로 요청못한다.

- redirect vs forward
  redirect는 http에서 배웠듯이 클라이언트가 어떠한 url 요청하면 서버가 응답하는데 3XX와 Location 헤더에 변경된 url을내려주고 다시 클라이언트는 자동리다이렉트로 다시 요청하는 매커니즘이다. 즉 클라이언트는 서버로 두번 요청을하는것이고 클라이언트가 인지할수있고 url경로도 실제로 변경되어 요청되고 응답된다.(리다이렉트 이후 요청응답에서)

그러나 forward는 서버 내부에서 일어나는 호출이므로 클라이언트가 인지못함
`dispatch.forward(request, response)` : 다른 서블릿이나 JSP로 이동할수있는 기능임 forward를 사용해서 컨트롤러에서 뷰를 호출하는데 서버내부에서 다시호출이일어나므로 redirect와 다른점. 클라이언트가 인지못하고 url도 실제로 변경이안됨.

- 추가로 상대경로!
  `action="save"`하면 상대경로로인정되어 현재경로 /servlet-mvc/members/에서 save가추가된 `/servlet-mvc/members/save`가된다. `action="/save"`를 하면 절대경로로 /save가된다..

- 모델에 데이터를 저장하는방법은 httpservletrequest객체를 이용한다. `requset.setAttribute("키", value);` - MVC에서 모델은 모델에 데이터를담아서 뷰로전달하고 뷰는 모델을 통해서 비즈니스로직을 하나도몰라도 모델에서 데이터꺼내서 랜더링에만 집중할수있다.

# JSP 서블릿만으로 만든 MVC의 한계

- 중복되는 코드가 너무많다. 이를테면 viewPath에대한 dispatcher로 컨트롤러에서 뷰 호출하는 부분이 계속중복된다. 이러한 한게를 다음에 고쳐보자.
