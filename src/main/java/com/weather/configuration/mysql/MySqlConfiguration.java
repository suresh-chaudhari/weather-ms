package com.weather.configuration.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Suresh Chaudhari
 */
@Configuration
public class MySqlConfiguration {

    @Autowired
    private MySqlProperties properties;

    @Bean
    public DataSource dataSource() {

        DataSource dataSource = DataSourceBuilder
                .create()
                .username(properties.getUsername())
                .password(properties.getPassword())
                .url(properties.getUrl())
                .driverClassName(properties.getDriverClassName())
                .build();

        return dataSource;
    }

}
