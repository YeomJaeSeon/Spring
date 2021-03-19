package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 서블릿 이름은 최대한 클래스이름과 비슷하게 한거뿐임 과몰임 하지말자.
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // 웹브라우저에서 http 요청이들어오면 Was(서블릿 컨테이너)가 response, request객체를 만들어서
    // 서블릿에 던져줌.
    @Override // 서블릿호출되면 이 service메소드 호출됨. ctrl + o로 쉽게메서드생성함.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);
        // servlet이 없엇으면 개발자가 http requeset message를 다 파싱하면서 읽어서
        // 분석해야하는데 서블릿이 쉽게 request 메시지를 분석하는 기능을 제공.

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8"); // 문자 인코딩은 왠만하면 utf-8써야함
        // 위 contenttype이라던가 encoding type은 response헤더에 들어가는것이다.
        response.getWriter().write("hello " + username); // http response body에 데이터가 들어감.


        // 서블릿을 이용해서 이렇게 쉽게 요청이나 응답에대한 처리를 할수있음. 서블릿 없었으면
        // 이거다 파싱하면,, response 메시지 다 하나씩만들고 마이 복잡...
    }
}
