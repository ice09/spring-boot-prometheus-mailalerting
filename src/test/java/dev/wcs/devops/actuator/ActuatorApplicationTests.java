package dev.wcs.devops.actuator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "healthcheck-url=" })
class ActuatorApplicationTests {

	@Test
	void contextLoads() {
	}

}
