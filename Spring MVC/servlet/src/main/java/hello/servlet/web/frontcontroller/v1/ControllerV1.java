package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 이 인터페이스가중요함 이 인터페이스에 맞춰서 form컨트롤러, save컨트롤러, list컨트롤러가 구현되어있음.
// 이 인터페이스 메서드를 오버라이딩한상태로.
public interface ControllerV1 {

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
