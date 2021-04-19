package study2.fighting.findbean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study2.fighting.AppConfig;

public class FindBeans {
    public static void main(String[] args) {
        findAllBeans();
    }
    static AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    static void findAllBeans(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("name = " + beanDefinitionName + ", bean value : " + ac.getBean(beanDefinitionName));
        }
    }
}
