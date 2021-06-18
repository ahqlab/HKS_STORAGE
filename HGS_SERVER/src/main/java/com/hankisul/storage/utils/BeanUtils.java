package com.hankisul.storage.utils;

import org.springframework.context.ApplicationContext;

import com.hankisul.storage.config.ApplicationContextProvider;


public class BeanUtils {
	
	public static Object getBean(String beanName) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getBean(beanName);
    }
}
