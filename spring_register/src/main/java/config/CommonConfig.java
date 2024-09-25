package config;

import cn.itcast.pojo.Country;
import cn.itcast.pojo.Province;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    @Bean
    public Country country(){
        return new Country();
    }
    //若要使用ioc
    @Bean
    public Province province(Country country){
        System.out.println("Province:"+country);
        return new Province();
    }
}
