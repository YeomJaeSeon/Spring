package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

// json을 객체로 파싱하는 역할을한다.
// json은 주로 그대로 사용하기보단 객체로 바꿔사용함.
@Getter @Setter // 롬복으로하면 getter setter아래처럼안해도됨.
public class HelloData {
    private String username;
    private int age;

    // 자바빈 접근법, 자바 프로퍼티 접근법
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

}
