package com.harrison;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Harrison
 * @date: 2023/2/21 21:24
 * @Description: TODO
 */
@SpringBootApplication
@MapperScan("com.harrison.mapper")
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }
}
