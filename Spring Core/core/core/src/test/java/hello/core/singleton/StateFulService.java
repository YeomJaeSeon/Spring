package hello.core.singleton;

public class StateFulService {
    private int price; // 상태를 유지하는 멤버변수
    // 싱글턴 객체 사용할떄 이러면안됨.. 상태를 유지하는 멤버변수를 만들면안됨. 왜? 사용자가 변경할수도있으닌까. 유지되는 값이있으면안된다.
    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

}
