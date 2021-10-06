package com.weather.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Suresh Chaudhari
 */
@Configuration
@ConfigurationProperties("weather-api")
@Getter
@Setter
public class WeatherApiProperties {

    private String url;
    private String appKey;
}
