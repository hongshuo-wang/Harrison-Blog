package com.harrison;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Harrison
 * @date: 2023/2/11 23:09
 * @Description: 启动类
 */

@SpringBootApplication
@MapperScan("com.harrison.mapper")
public class MyBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }
}
