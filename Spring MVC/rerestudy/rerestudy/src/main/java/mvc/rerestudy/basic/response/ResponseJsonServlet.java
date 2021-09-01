package mvc.rerestudy.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import mvc.rerestudy.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    //객체를 json으로 변환해서 응답하기 위한 라이브러리(Jackson)
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "application/json");
        request.setCharacterEncoding("utf-8");

        HelloData data = new HelloData();
        data.setUsername("yeom");
        data.setAge(20);

        String result = objectMapper.writeValueAsString(data);

        response.getWriter().write(result);
    }
}
