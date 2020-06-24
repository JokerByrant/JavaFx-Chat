package com.jfx.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ResourceBundle;

/** 
* <b>ClassName</b>: AppJavaConfig <br/> 
*
* <b>Description</b>: AppJavaConfig <br/> 
*
* <b>Date</b>: Apr 22, 2019 1:13:50 PM <br/> 
* 
* @author pdai
* @version Apr 22, 2019
*
*/
@Configuration
public class AppJavaConfig {
    // 将ResourceBundle注册到Spring容器中
    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }
}
