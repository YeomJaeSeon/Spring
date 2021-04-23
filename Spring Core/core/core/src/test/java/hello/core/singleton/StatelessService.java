package hello.core.singleton;

public class StatelessService {
    // 상태를 유지하는 멤버변수대신 지역변수를 이용하자. 싱글턴 객체는 상태유지하는 필드 쓰면 절대안됨.
    public int order(String itemName, int price){
        System.out.println("itemName : " + itemName + " price : " + price);
        return price;
    }
}
