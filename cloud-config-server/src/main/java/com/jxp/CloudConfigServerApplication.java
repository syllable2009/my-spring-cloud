package com.jxp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class CloudConfigServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>CloudConfigServerApplication服务启动执行 <<<<<<<<<<<<<");
    }
}
