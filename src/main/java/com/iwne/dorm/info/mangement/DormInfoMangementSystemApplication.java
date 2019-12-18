package com.iwne.dorm.info.mangement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.iwne.dorm.info.mangement.dao")
@SpringBootApplication
public class DormInfoMangementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormInfoMangementSystemApplication.class, args);
    }

}
