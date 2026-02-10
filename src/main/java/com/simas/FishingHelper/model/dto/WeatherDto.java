package com.simas.FishingHelper.model.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class WeatherDto {
    private String description;
    private double temperature;
    private double windSpeed;
    private int pressure;
    private double moonPhase;
}




