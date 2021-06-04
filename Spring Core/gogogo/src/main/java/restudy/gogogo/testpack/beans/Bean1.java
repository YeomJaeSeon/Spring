package restudy.gogogo.testpack.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class Bean1 {
    private final Bean2 bean2;
    private final Bean3 bean3;
    private int name;

    @Autowired
    public Bean1(Bean2 bean2, Bean3 bean3, @Nullable int name) {
        name = (int)(Math.random() * 100) + 1;
        this.bean2 = bean2;
        this.bean3 = bean3;
        this.name = name;
        System.out.println("bean2 = " + bean2);
        System.out.println("bean3 = " + bean3);
        System.out.println("name = " + name);
    }
}
