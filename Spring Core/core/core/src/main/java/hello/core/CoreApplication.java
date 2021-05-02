package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
		System.out.println("SpringBootApplication 내부 application Context 생성 및 컴포넌트 스캔 시작");
	}
}
