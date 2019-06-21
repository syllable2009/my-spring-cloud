package com.jxp.happy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@SpringBootApplication
public class HapplyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HapplyApplication.class, args);
    }

    @Value("${happy}")
    String foo2;

    @GetMapping("/test")
    public ResponseEntity testConfig() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", foo2);
        return ResponseEntity.ok(map);
    }
}
