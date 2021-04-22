package hello.core.singletontest;

public class SingleTon {
    private static final SingleTon instance = new SingleTon();

    private SingleTon(){

    }

    public static SingleTon getInstance(){
        return instance;
    }
}
