# GET방식으로 쿼리파라미터 들어올때 서블릿 에서 어케받는지 해봄

- request.getParameter("쿼리 key이름");
  (여기서 말하는 request는 서블릿 객체의 httpServletRequest 매개변수임)
- 이렇게접근함
- 하나의 쿼리 key에 대해서 여러개의 복수 쿼리values가 들어오면 `request.getParameterValues("쿼리 key이름");`으로 한다 근데 거의 이경우없겠지 ㅋㅋ
