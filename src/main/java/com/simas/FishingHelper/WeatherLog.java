package com.simas.FishingHelper;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class WeatherLog {
    private String description;
    private double temperature;
    private double windSpeed;
}




