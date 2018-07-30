package com.jxp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigServer
@SpringBootApplication
public class CloudConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServerApplication.class, args);
    }

    @Value("hello")
    String hello;
    @Value("app.id")
    String appId;

    public ResponseEntity getParam() {
        Map map = new HashMap<>();
        map.put("hello", hello);
        map.put("app,id", appId);
        return ResponseEntity.ok(map);
    }
}
