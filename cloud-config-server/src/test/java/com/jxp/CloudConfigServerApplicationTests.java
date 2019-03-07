package com.jxp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.test.context.junit4.SpringRunner;

@EnableConfigServer
@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudConfigServerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
