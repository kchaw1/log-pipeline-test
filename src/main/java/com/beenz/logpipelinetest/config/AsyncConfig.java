package com.beenz.logpipelinetest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);  // 최소 스레드 수
        executor.setMaxPoolSize(16);  // 최대 스레드 수
        executor.setQueueCapacity(32);  // 대기 중인 작업의 큐 용량
        executor.setThreadNamePrefix("async-thread-");
        return executor;
    }
}
