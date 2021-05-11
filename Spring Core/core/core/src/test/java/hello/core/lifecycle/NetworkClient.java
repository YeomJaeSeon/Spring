package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
// spring에 종속되어있는 애너테이션이아니다~ javax다.

public class NetworkClient{

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        //connect()
        //call("초기화 연결 메시지");
         // 이런초기화작업은 생성자에서하지않고 관심사를 분리하는게좋다. 초기화작업과 객체 생성의 관심사를 분리하면 유지보수가 편해진다.
        // 생성자(객체 생성) -필수 파라미터를 받아서 메모리 할당 객체 생성역할
        // 초기화 - 위에서 생성된 값을 이용해서 외부 커넥션등 무거운 작업
        // 그러므로 객체 생성하는 **생성자**와 해당 객체를 통해 외부 커넥션등 무거운작업을 하는 **초기화**를 분리하는게 좋다.
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작할때 호출하는 메서드
    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call(String message){
        System.out.println("call : " + url +", message= " + message);
    }

    //서비스 종료시 호출되는 메서드
    public void disconnect(){
        System.out.println("close " + url);
    }

    // 의존관계 주입이 끝나면 이 메시드 호출한다. - 초기화콜백 기능 -

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    // 소멸전 콜백
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
