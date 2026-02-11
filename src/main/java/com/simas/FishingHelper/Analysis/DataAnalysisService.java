package com.simas.FishingHelper.Analysis;

import com.simas.FishingHelper.Model.Dtos.CatchDto;
import com.simas.FishingHelper.Model.Dtos.SuggestionDto;
import com.simas.FishingHelper.Model.Dtos.WeatherDto;
import com.simas.FishingHelper.Repository.CatchLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataAnalysisService {
    private final CatchLogRepository repository;

    public List<SuggestionDto> getSuggestionsForSpecies(String species, WeatherDto currentWeather) {
        // Get all historical catches for this species
        List<CatchDto> catches = repository.findBySpecies(species);

        // Score each catch based on similarity to current conditions
        Map<String, SuggestionDto> scoredConditions = new HashMap<>();

        for (CatchDto catchDto : catches) {
            double score = calculateSimilarity(currentWeather, catchDto.getWeather());
            String key = categorizeWeather(catchDto.getWeather());

            scoredConditions.computeIfAbsent(key, k -> new SuggestionDto())
                    .addCatch(score, catchDto.getWeight());
        }

        // Convert to suggestions and sort by score
        return scoredConditions.entrySet().stream()
                .map(e -> new SuggestionDto(
                        e.getKey(),
                        e.getValue().getAverageScore(),
                        e.getValue().getCount(),
                        e.getValue().getAverageWeight(),
                        generateReasoning(e.getKey(), e.getValue())
                ))
                .sorted(Comparator.comparing(SuggestionDto::getScore).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    private double calculateSimilarity(WeatherDto current, WeatherDto historical) {
        double tempScore = 1.0 - Math.abs(current.getTemperature() - historical.getTemperature()) / 30.0;
        double windScore = 1.0 - Math.abs(current.getWindSpeed() - historical.getWindSpeed()) / 20.0;
        double pressureScore = 1.0 - Math.abs(current.getPressure() - historical.getPressure()) / 50.0;
        double moonScore = 1.0 - Math.abs(current.getMoonPhase() - historical.getMoonPhase());

        return (tempScore * 0.3 + windScore * 0.25 + pressureScore * 0.25 + moonScore * 0.2);
    }
//    public BaitSuggestionDto queryBestBaitForSpecie(String specie) {
//        List<BaitSuggestionDto> baitSuggestionList = repository.getMostEffectiveBait(specie);
//        baitSuggestionList.sort((a, b) -> Long.compare(b.getCount(), a.getCount()));
//        if (baitSuggestionList.isEmpty()) {
//            throw new NoDataException("No bait data available for species: " + specie);
//        }
//        return baitSuggestionList.get(0);
//    }
//
//    //Returns weather conditions with the most catches
//    public WeatherDto queryBestWeatherForSpecie(String specie) {
//        List<WeatherDto> listOfWeatherConditionsTo = repository.findWeatherWithMostCatches(specie);
//
//    }
}