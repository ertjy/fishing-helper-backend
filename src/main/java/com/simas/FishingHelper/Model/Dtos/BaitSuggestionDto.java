package com.simas.FishingHelper.Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaitSuggestionDto {
    private String bait;
    private Long count;
}
