# httpServletResponse를 이용해서 respone body에 텍스트와 html데이터를 넣어서 응답해보자.

- text는 너무 간단 우리가 자주했었따.
- `writer().println("ok");`

- html을 내려보낼떈 content-type지정해줘야한다. 이전에 했던 `setContenType()`을 통해서, 우린 html내려줄꺼닌까 `text/html`로 바디 데이터타입 지정해준다.
- 인코딩도해준다.

- 그리고 텍스트를 response body에 넣어서 응답했던 것처럼 html만 넣어서 내려주면됨

```
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println(" <div>안녕?</div>");
        writer.println("</body>");
        writer.println("</html>");
```
