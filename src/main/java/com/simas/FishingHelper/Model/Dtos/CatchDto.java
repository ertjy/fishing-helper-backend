package com.simas.FishingHelper.Model.Dtos;

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
    private Double lon;
    private Double lat;
    private double weight;
    private String baitUsed;

    @Embedded
    private WeatherDto weather;
}
