package com.simas.FishingHelper.Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionDto {
    private String species;
    private String bait;
    private String location;
}