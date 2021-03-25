package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    // jackson이라는 라이브러리를 통해서 json을 객체로 변경해줘야함. 이 라이브러리는
    //spring boot가 기본적으로 가지고있음
    private ObjectMapper objectMapper = new ObjectMapper(); // spring boot가 기본으로 가지고잇음


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("message Body : " + messageBody);
        // 이렇게해도 찍힌다. 어? text랑 같네 ㅇㅇ 같아. 왜냐면 inputStream은 그냥
        // http body의 데이터를 읽어오는거기떄문에 json도 body에존재하는 데이터고문자닌까
        // 가능함.

        // json 객체로 변환. json은 주로 위처럼 문자로사용하기보단 객체로 바꿔서 사용함
        // 이떄 주의할점은 jackson이라는 라이브러리를 통해서 json을 객체로 파싱한당.
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData username = " + helloData.getUsername());
        System.out.println("helloData age = " + helloData.getAge());

        response.getWriter().write("ok");
    }
}
