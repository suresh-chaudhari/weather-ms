package com.loyalty.one.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TemperatureMetric {

    private float temp;
    private int pressure;
    private int humidity;
    private float temp_min;
    private float temp_max;
}
