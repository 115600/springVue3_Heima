package org.wineky.spring_register;

import anno.EnableCommonConfig;
import cn.itcast.pojo.Country;
import config.CommonConfig;
import config.CommonImportSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.naming.Context;

@SpringBootApplication
//@Import(CommonImportSelector.class)
@EnableCommonConfig //使用组合注解
public class SpringRegisterApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringRegisterApplication.class, args);
        Country country = context.getBean(Country.class);
        System.out.println(country);
        System.out.println(context.getBean("province"));
    }


}
