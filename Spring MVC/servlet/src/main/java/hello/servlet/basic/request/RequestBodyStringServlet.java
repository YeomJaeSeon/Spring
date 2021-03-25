package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = requset.getInputStream(); // byte코드임
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// byte를 문자로바꿀땐
//어떤 인코딩인지 알려줘야함 역도 동일함.(UTF_8)
        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
   }
}
