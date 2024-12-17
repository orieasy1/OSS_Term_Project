package com.team3.ossproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OssprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssprojectApplication.class, args);
    }

}
