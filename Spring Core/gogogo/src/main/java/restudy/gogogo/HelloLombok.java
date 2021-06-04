package restudy.gogogo;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(12);
        helloLombok.setName("name");

        System.out.println(helloLombok.getName());

        System.out.println(helloLombok);
    }
}
