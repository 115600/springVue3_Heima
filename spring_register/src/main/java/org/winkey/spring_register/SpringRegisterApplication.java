package org.winkey.spring_register;

import anno.EnableCommonConfig;
import cn.itcast.pojo.Country;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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
