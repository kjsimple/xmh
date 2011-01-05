package com.gydoc.xmh;

import com.gydoc.xmh.service.InitDataService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 */
public class SpringUtil {
    
    private static AbstractApplicationContext springCtx;
    
    static {
        springCtx = new ClassPathXmlApplicationContext("spring.xml");
        springCtx.registerShutdownHook();

        InitDataService iService = (InitDataService) springCtx.getBean("initData");
        iService.initData();
    }
    
    private SpringUtil() {
        
    }
    
    public static ApplicationContext getSpringContext() {
        return springCtx;
    }
    
    public static Object getBean(String id) {
        return getSpringContext().getBean(id);
    }
    
}
