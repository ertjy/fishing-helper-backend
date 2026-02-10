package com.simas.FishingHelper.Analysis;

import com.simas.FishingHelper.Model.Dtos.BaitSuggestionDto;
import com.simas.FishingHelper.Repository.CatchLogRepository;
import com.simas.FishingHelper.exceptions.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataAnalysisService {
    private final CatchLogRepository repository;

    public BaitSuggestionDto analyseBestBaitForSpecie(String specie) {
        List<BaitSuggestionDto> baitSuggestionList = repository.getMostEffectiveBait(specie);
        baitSuggestionList.sort((a, b) -> Long.compare(b.getCount(), a.getCount()));
        if (baitSuggestionList.isEmpty()) {
            throw new NoDataException("No bait data available for species: " + specie);
        }
        return baitSuggestionList.get(0);

    }
}