package com.jxp.configcenter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigServer
@SpringBootApplication
public class ConfigCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApplication.class, args);
    }

    @Value("${foo}")
    String foo2;

    @GetMapping("/test")
    public ResponseEntity testConfig() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", foo2);
        return ResponseEntity.ok(map);
    }
}
