package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InaccessibleObjectException;

// spring mvc에도 handleradapter라는게 있당.
public interface MyHandlerAdapter {

    // 해당 핸들러가 어댑터와 맞는지 확인 맞으면 true, 아니면 false
    boolean supports(Object handler);

    // 핸들러를 인자로 넣어서 어뎁터의 handler메서드를 호출.
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, InaccessibleObjectException;
}
