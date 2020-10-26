package com.fzw.authority.config;

import com.fzw.authority.properties.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeChatConfig {

    /**
     * Redsi配置属性
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public RedisProperties redisProperties(){
        return new RedisProperties();
    }
}
