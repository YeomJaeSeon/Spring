package mvc.rerestudy.web.frontcontroller.v5;

import mvc.rerestudy.domain.member.MemberRepository;
import mvc.rerestudy.web.frontcontroller.ModelView;
import mvc.rerestudy.web.frontcontroller.MyView;
import mvc.rerestudy.web.frontcontroller.v3.controller.MemberFormControllerV3;
import mvc.rerestudy.web.frontcontroller.v3.controller.MemberListControllerV3;
import mvc.rerestudy.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import mvc.rerestudy.web.frontcontroller.v4.controller.MemberFormControllerV4;
import mvc.rerestudy.web.frontcontroller.v4.controller.MemberListControllerV4;
import mvc.rerestudy.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import mvc.rerestudy.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import mvc.rerestudy.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5(@Autowired MemberRepository memberRepository){
        initHandlerMappingMap(memberRepository);
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter()); //V4 추가
    }

    private void initHandlerMappingMap(MemberRepository memberRepository) {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3(memberRepository));
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3(memberRepository));

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new
                MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new
                MemberSaveControllerV4(memberRepository));
        handlerMappingMap.put("/front-controller/v5/v4/members", new
                MemberListControllerV4(memberRepository));
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);
        if(handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(request, response, handler);

        MyView view = viewResolver(mv.getViewName());
        view.render(mv.getModel(), request, response);


    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler))
                return adapter;
        }
        throw new IllegalArgumentException("해당 핸들러를 어뎁터에서 찾을수가 없습니다. handler = " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        Object handler = handlerMappingMap.get(requestURI);
        return handler;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
