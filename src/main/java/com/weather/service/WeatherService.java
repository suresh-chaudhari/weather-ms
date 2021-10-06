package com.weather.service;

import com.weather.configuration.WeatherApiProperties;
import com.weather.model.dao.PostsDao;
import com.weather.model.response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherService {

    @Autowired
    private WeatherApiProperties weatherApiProperties;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * It will fetch the weather information and geolocation details from open weather api by City name and set it to post
     * dao object.
     * @param postsDao
     */
    public void addWeatherInformation(PostsDao postsDao, String city) {

        // create weather api final url
       final String url = weatherApiProperties.getUrl()+ "?" +"appid="+weatherApiProperties.getAppKey()
               +"&q="+city+"&units=metric";

        WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);
        log.info("Fetched Weather Data from open weather api: "+ weatherResponse);

        postsDao.setLatitude(weatherResponse.getCoord().getLat());
        postsDao.setLongitude(weatherResponse.getCoord().getLon());
        postsDao.setTemperature(weatherResponse.getTemperatureMetric().getTemp());
    }
}
