package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final ObjectProvider<MyLogger> myLoggerProvider;
    // 스프링 컨테이너의 빈을 꺼내와서 DI를하는게아닌LookUp할 객체를 받아옴 - 에러 X

    public void logic(String id){
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("id : " + id);
    }
}