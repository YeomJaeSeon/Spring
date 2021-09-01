package mvc.rerestudy.web.servlet;

import lombok.RequiredArgsConstructor;
import mvc.rerestudy.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();

        writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
"    <title>Title</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "<form action=\"/servlet/members/save\" method=\"post\">\n" +
        "    username: <input type=\"text\" name=\"username\" />\n" +
        "    age:      <input type=\"text\" name=\"age\" />\n" +
        " <button type=\"submit\">전송</button>\n" + "</form>\n" +
        "</body>\n" +
        "</html>\n");
    }
}