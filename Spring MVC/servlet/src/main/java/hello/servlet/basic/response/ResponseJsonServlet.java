package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

@WebServlet(name="responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        //content-type : applicaiton/json으로 해야됨
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8"); //이래야 한글 안깨짐

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        //객체이므로 json으로바꿔서 response body에 넣어서 내려줘야함
        String result = objectMapper.writeValueAsString(helloData);
        // 객체를 문자로바꿔준다.
        PrintWriter writer = response.getWriter();
        writer.println(result);
    }
}
