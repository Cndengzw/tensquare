package com.tensquare.article.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import utils.IdWorker;

/**
 * @author Deng Zhiwen
 * @date 2021/4/16 10:42
 */
@Configuration
public class IdWorkerConfig {

    @Bean
    public IdWorker createIdWorker() {
        return new IdWorker(1, 1);  // 1, 1 是自己指定的，具体可以看参数介绍
    }

}
