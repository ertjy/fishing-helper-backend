package com.simas.FishingHelper.Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionDto {
    private String condition;
    private double score;
    private long catchCount;
    private double avgWeight;
    private String reasoning;

    public void addCatch(double score, double weight) {
        this.totalScore += score;
        this.count++;
        this.totalWeight += weight;
    }

    public double getAverageScore() {
        return count > 0 ? totalScore / count : 0.0;
    }

    public double getAverageWeight() {
        return count > 0 ? totalWeight / count : 0.0;
    }
}