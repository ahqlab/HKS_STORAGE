package com.hankisul.storage.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesViewResolver;

import com.hankisul.logger.LoggerBeanPostProcessor;
import com.hankisul.storage.interceptor.LogInterceptor;
import com.hankisul.storage.interceptor.LoginInterceptor;
import com.hankisul.storage.view.ConnectStatsExcelView;

@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages = { "com.hankisul.storage.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public BeanPostProcessor loggerBeanPostProcessor() {
		return new LoggerBeanPostProcessor();
	}

	@Bean
	public AbstractExcelView connectStatsExcelView() {
		return new ConnectStatsExcelView();
	}

	@Bean
	public HandlerInterceptor logInterceptor() {
		return new LogInterceptor();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		String[] patterns = { "/**" };

		String[] exPatterns = { "/", "/login", "/rest/**", "/test/sms", "/storeage/test/sms", "/admin/**",
				"/server/**" };

		registry.addInterceptor(logInterceptor()).addPathPatterns(new String[] { "/**" });
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns(patterns).excludePathPatterns(exPatterns);
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(new String[] { "/**" }).addResourceLocations(new String[] { "/resources/" });
	}

	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "classpath:tilesdef.xml" });
		return configurer;
	}

	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver resolver = new TilesViewResolver();
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver resolver = new BeanNameViewResolver();
		resolver.setOrder(0);
		return resolver;
	}
}