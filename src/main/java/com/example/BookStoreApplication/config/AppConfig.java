package com.example.BookStoreApplication.config;

import com.example.BookStoreApplication.util.TokenUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TokenUtility tokenUtility(){
        return new TokenUtility();
    }
}
