package hello.servlet;

import hello.servlet.web.springmvc.v1.SpringMemberFormControllerV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan // 즉, 서블릿 자동등록
// 스프링 부트에서 서블릿쓰려면 이 애노테이션 지원해줌.
// 내 패키지 하위패키지 뒤져서 서블릿 찾아서 자동으로 서블릿을 등록해서 실행하도록 도와줌
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

	// 자바코드로 스프링빈등록
//	@Bean
//	SpringMemberFormControllerV1 springMemberFormControllerV1(){
//		return new SpringMemberFormControllerV1();
//	}
}
