package com.simas.FishingHelper.Controller;

import com.simas.FishingHelper.Analysis.DataAnalysisService;
import com.simas.FishingHelper.exceptions.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/suggestions")
@RequiredArgsConstructor
public class SuggestionsController {
    private final DataAnalysisService dataAnalysisService;

    @GetMapping("/bait-suggestions")
    public ResponseEntity<?> getBestBaitForSpecie(@RequestBody String specie) {
        try {
            return ResponseEntity.ok(dataAnalysisService.analyseBestBaitForSpecie(specie));
        } catch (NoDataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong: " + e.getMessage());
        }
    }
}
