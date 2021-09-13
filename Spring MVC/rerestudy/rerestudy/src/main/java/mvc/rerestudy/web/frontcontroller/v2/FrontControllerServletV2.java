package mvc.rerestudy.web.frontcontroller.v2;

import mvc.rerestudy.domain.member.MemberRepository;
import mvc.rerestudy.web.frontcontroller.MyView;
import mvc.rerestudy.web.frontcontroller.v2.controller.MemberFormControllerV2;
import mvc.rerestudy.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerV2Map = new HashMap<>();

    public FrontControllerServletV2(@Autowired MemberRepository memberRepository) {
        controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2(memberRepository));
        controllerV2Map.put("/front-controller/v2/members", new MemberSaveControllerV2(memberRepository));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();

        ControllerV2 controllerV2 = controllerV2Map.get(requestURI);
        if(controllerV2 == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controllerV2.process(req, resp);
        view.render(req, resp);
    }
}
