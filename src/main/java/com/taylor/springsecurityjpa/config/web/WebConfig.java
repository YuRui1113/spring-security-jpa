/*
 * File: src\main\java\com\taylor\springsecurityjpa\config\web\WebConfig
 * Project: spring-security-jpa
 * Created Date: Monday, December 25th 2023, 4:42:06 pm
 * Author: Rui Yu
 * -----
 * Last Modified: Thursday, 11th January 2024 6:39:45 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * 
 * All shall be well and all shall be well and all manner of things shall be well.
 * Nope...we're doomed!
 * -----
 * HISTORY:
 * Date      	By	Comments
 * ----------	---	----------------------------------------------------------
 */

package com.taylor.springsecurityjpa.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/resources/",
            "classpath:/static/"
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
                .setCachePeriod(3000);
    }
}