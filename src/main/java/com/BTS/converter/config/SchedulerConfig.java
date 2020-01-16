/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 *
 * @author hp
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer{
    private final int POOL_SIZE = 10;

    @Override
    public void configureTasks(ScheduledTaskRegistrar str) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        
        taskScheduler.setPoolSize(POOL_SIZE);
        taskScheduler.setThreadNamePrefix("My-Shceduller-task-cob");
        taskScheduler.initialize();
        
        str.setTaskScheduler(taskScheduler);
    }
    
            
}
