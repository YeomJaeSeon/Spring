package restudy.gogogo.singleton;

public class SingletonService {

    // static레벨
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    // 외부에서생성자호출 (객체생성)막기 - private 생성자
    private SingletonService(){

    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
