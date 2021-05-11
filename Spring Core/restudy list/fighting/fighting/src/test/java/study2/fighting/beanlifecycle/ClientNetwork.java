package study2.fighting.beanlifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ClientNetwork {
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public ClientNetwork() {
        System.out.println("생성자 호출");
        // 객체생성하는 생성자에서 외부 커넥션 설정하는 초기화는 오내만하면 하지말자. 관심사를 분리하자.
    }

    public void connect(){
        System.out.println("connect : " + url);
    }
    public void call(String message){
        System.out.println("call : " + message + " url : " + url);
    }

    @PostConstruct // 의존관계 주입 이후 실행되는 초기화 콜백 메서드로등록
    public void init(){
        connect();
        call("초기화 콜백 실행");
    }

    @PreDestroy // 스프링빈 소멸직전에 호출되야할 소멸전 콜백 메서드로 등록
    public void close(){
        System.out.println("disconnect : " + url);
    }
}
