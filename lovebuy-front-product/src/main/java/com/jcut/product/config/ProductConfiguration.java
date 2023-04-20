package com.jcut.product.config;

import com.jcut.config.CacheConfiguration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/18 20:33
 **/
@Configuration
public class ProductConfiguration extends CacheConfiguration {
    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }
}
