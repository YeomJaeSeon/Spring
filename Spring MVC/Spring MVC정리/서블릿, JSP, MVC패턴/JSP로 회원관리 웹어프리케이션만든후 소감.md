# 서블릿으로 HTML, 뷰만드는데 너무 힘들어서 JSP를 이용해서 해봤따. 뷰만드는건 확실히 괜찮다. 서블릿보다.

- JSP사용하려면 일단 라이브러리 추가해야한다.

- JSP도 서버 내부에서 servlet으로 변환되므로 httpservletRequest, httpServletResponse같은 객체를 예약어(키워드)로그냥 사용할수가 있다.

- 그러나 비즈니스로직 처리하는 JAVA코드랑 뷰담당하는 HTML이랑 한 파일에있따. 지금은 간단하지만 프로젝트가 커지면 이게 한계가 있다. 그래서 MVC패턴을 도입해보자.

- JAVA코드를 처리하는데 괜찮은 서블릿과 HTML같은 뷰를 만드는데 특화된 JSP를 이용해 MVC의 V는 JSP가 C는 서블릿이 처리하게끔해서 다음시간에 MVC패턴을만들어서 해보자.
