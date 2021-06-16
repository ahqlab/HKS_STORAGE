package net.octacomm.sample.utils;

import org.springframework.context.ApplicationContext;

import net.octacomm.sample.config.ApplicationContextProvider;


public class BeanUtils {
	
	public static Object getBean(String beanName) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getBean(beanName);
    }
}
