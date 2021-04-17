package com.tensquare.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

/**
 * @author Deng Zhiwen
 * @date 2021/4/5 22:11
 */
@SpringBootApplication
//Mapper扫描注解
@MapperScan("com.tensquare.article.dao")
public class ArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }

    // bean注解需要有 @Configuration 注解，这里@SpringBootApplication里面的@SpringBootConfiguration含有该注解
//    @Bean
//    public IdWorker createIdWorker() {
//        return new IdWorker(1, 1);  // 1, 1 是自己指定的，具体可以看参数介绍
//    }

}
