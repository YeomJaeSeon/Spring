package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링 부트를 이용하면 (우리는 이걸 이용하지 않고있긴하지만.)
// 내부적으로 스프링 컨테이너가 생성이되어서 스프링빈들이 딲딲 등록이되어지고 스프링빈간의 의존관계도 설정이된다.
// 이 애너테이션 내부까보면 @ComponentScan이 있는걸볼수가있다.
@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
		System.out.println("SpringBootApplication 내부 application Context 생성 및 컴포넌트 스캔 시작");
	}
}
