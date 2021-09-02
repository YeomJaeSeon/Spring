package mvc.rerestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class RerestudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RerestudyApplication.class, args);
	}
}
