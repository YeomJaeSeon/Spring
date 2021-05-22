package restudy.gogogo.test;

class A{
    I i; // 1
    public A(I i){
        this.i = i; //2
    }
    void method(){
        i.method(); //3
    }
}

interface I{
    void method();
}
class B implements I{

    @Override
    public void method() {
        System.out.println("B.method");
    }
}
class C implements I{

    @Override
    public void method() {
        System.out.println("C.method");
    }
}

class D implements I{

    @Override
    public void method() {
        System.out.println("D.method");
    }
}

public class Test {
    public static void main(String[] args) {
        A a = new A(new D()); // 4
        a.method();
    }
}
