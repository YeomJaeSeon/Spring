package restudy.gogogo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import restudy.gogogo.service.OrderService;

@SpringBootTest
class GogogoApplicationTests {

	@Autowired
	OrderService orderService;

	@Test
	void contextLoads() {
		System.out.println("orderService = " + orderService);
	}

}
