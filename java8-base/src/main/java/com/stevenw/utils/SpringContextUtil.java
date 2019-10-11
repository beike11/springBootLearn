package com.stevenw.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import javax.annotation.PostConstruct;

/**
 * @author stevenw
 * @date 2019/9/3
 */
@Component
public class SpringContextUtil extends WebApplicationObjectSupport {
    private static ApplicationContext applicationContext;

    public SpringContextUtil() {
        super();
    }
    @PostConstruct
    public void init(){
        SpringContextUtil.applicationContext = getWebApplicationContext();
    }

    public static Object getBean(String name) throws BeansException {
        return  applicationContext.getBean(name);
    }


}
