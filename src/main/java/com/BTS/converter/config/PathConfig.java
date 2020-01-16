/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 *
 * @author Reza
 */
@Configuration
@EnableWebMvc
public class PathConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/css/**",
                "/js/**",
                "/images/**",
                "/error/**",
                "/bootstrap/**",
                "/fontawesome/**",
                "/jquery/**",
                "/DataTables/**")
                .addResourceLocations(
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/images/",
                        "classpath:/static/jquery/",
                        "classpath:/static/error/",
                        "classpath:/static/bootstrap/",
                        "classpath:/static/fontawesome/",
                        "classpath:/static/DataTables/");
    }

}
