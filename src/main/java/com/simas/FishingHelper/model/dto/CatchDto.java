package com.simas.FishingHelper.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatchDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String species;
    private LocalDateTime dateTime;
    private String lon;
    private String lat;
    private double weight;
    private String baitUsed;

    @Embedded
    private WeatherDto weather;
}
