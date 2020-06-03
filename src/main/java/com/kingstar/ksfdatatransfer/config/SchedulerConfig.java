package com.kingstar.ksfdatatransfer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/24 16:00
 */
@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(concurrentTaskScheduler());
    }

    @Bean
    public ScheduledExecutorService concurrentTaskScheduler(){
        ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(5);
        executorService.setMaximumPoolSize(8);
        executorService.setRejectedExecutionHandler(new ScheduledThreadPoolExecutor.CallerRunsPolicy());
        return executorService;
    }
}
