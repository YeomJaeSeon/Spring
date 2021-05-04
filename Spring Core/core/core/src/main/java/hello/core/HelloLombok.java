package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 롬복 완전편해
@Getter @Setter @ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(10);
        helloLombok.setName("염재선");

        System.out.println(helloLombok);
    }
}
