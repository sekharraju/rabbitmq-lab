package com.jk.explore.rabbitmq.consumer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

/**
 * @author kondurj
 */
@EnableWebSecurity
@Configuration
public class ConsumerAppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.requestMatcher(AnyRequestMatcher.INSTANCE).authorizeRequests().anyRequest().permitAll();
    }

}
