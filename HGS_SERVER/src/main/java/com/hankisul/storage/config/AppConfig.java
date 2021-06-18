package com.hankisul.storage.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import com.hankisul.logger.LoggerBeanPostProcessor;

@Configuration
@ComponentScan(basePackages = "com.hankisul.storage.service")
@EnableCaching
@Import(MyBatisConfig.class)
public class AppConfig {

	@Bean
	public BeanPostProcessor loggerBeanPostProcessor() {
		return new LoggerBeanPostProcessor();
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
	
	@Bean
	public ApplicationContextProvider applicationContextProvider() {
		return new  ApplicationContextProvider();
	}
}
