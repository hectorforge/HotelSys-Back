package com.hotelsys.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HotelsysApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelsysApiApplication.class, args);
    }

}
