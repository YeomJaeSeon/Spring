package hello.freeboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class FreeboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreeboardApplication.class, args);
	}

}
