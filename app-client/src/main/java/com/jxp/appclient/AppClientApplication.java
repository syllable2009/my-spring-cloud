package com.jxp.appclient;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AppClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppClientApplication.class, args);
    }

    @Value("${foo}")
    String foo;
    @Value("${app.id}")
    String appId;
    @Value("${app.domain}")
    String appDomain;

    @GetMapping("/hello")
    public ResponseEntity getParam() {
        Map map = new HashMap<>();
        map.put("id", appId);
        map.put("domain", appDomain);
        map.put("foo", foo);
        return ResponseEntity.ok(map.toString());
    }

}
