package com.touteii.shopping.upload.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ShoppingCorsConfiguration {

    @Bean
    public CorsFilter corsFilter(){
        //cors config target
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        //yong xu kua yu,if you want to take cookies,you can not use *
        corsConfiguration.addAllowedOrigin("http://manage.t-shopping.com");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedMethod("*");//*=get,post,put,delete everything
        corsConfiguration.addAllowedHeader("*");//yong xu xie dai ren he tou xin xi

        //cors instance
        UrlBasedCorsConfigurationSource corsConfigurationSource=new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        //return corsFilter  parmeter
        return new CorsFilter(corsConfigurationSource);
    }
}
