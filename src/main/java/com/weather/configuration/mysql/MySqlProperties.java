package com.weather.configuration.mysql;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Suresh Chaudhari
 */
@Configuration
@ConfigurationProperties("mysql.datasource")
@Getter
@Setter
public class MySqlProperties {

    private String url;
    private String username;
    private String password;
    private String maxPoolSize;
    private String driverClassName;
}
