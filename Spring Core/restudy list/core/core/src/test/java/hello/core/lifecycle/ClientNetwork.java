package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ClientNetwork {
    String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public ClientNetwork() {
        System.out.println("생성자 호출" + " url : " + url);
    }
    void connect(){
        System.out.println("connect() : " + url);
    }
    void call(String message){
        System.out.println("call message : " + message + "url : " + url);
    }
    void disconnect(){
        System.out.println("disconnect() : " + url);
    }

    //소멸 전
    @PreDestroy
    public void close(){
        disconnect();
    }

    // 스프링 컨테이너 생성-> 빈등록 -> 스프링빈의존관계 주입 완료되면 호출됨 - 초기화 콜백 메서드임
    // 생성 후(의존관계주입 끝난후)
    @PostConstruct
    public void init(){
        connect();
        call("초기화 시작");
    }
}
