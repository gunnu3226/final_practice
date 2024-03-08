package com.sparta.finalpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FinalpracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalpracticeApplication.class, args);
    }
}
