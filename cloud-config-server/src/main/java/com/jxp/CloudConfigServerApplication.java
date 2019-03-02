package com.jxp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigServer
@SpringBootApplication
public class CloudConfigServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServerApplication.class, args);
    }

    @Value("app.domain")
    String domain;
    @Value("app.id")
    String appId;

    @GetMapping("/hello")
    public ResponseEntity getParam() {
        Map map = new HashMap<>();
        map.put("id", appId);
        map.put("domain", domain);
        return ResponseEntity.ok(map.toString());
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>CloudConfigServerApplication服务启动执行 <<<<<<<<<<<<<");
    }
}
