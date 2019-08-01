package com.project.mc.config;

import com.project.mc.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
      @Value("${idworker.workerId}")
    private Long workerId;

    @Bean
    public IdWorker idWorker(){
        IdWorker idWorker = new IdWorker(workerId);
        return idWorker;
    }

}
