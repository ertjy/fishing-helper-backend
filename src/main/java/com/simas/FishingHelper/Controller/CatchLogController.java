package com.simas.FishingHelper.Controller;

import com.simas.FishingHelper.Model.Dtos.CatchDto;
import com.simas.FishingHelper.Service.CatchLogService;
import com.simas.FishingHelper.Model.Dtos.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.simas.FishingHelper.Service.WeatherService.getCurrentWeather;

@RestController
@RequestMapping("/api/fish-logs")
@RequiredArgsConstructor
public class CatchLogController {
    private final CatchLogService catchLogService;

    @PostMapping
    public ResponseEntity<CatchDto> logCatch(@RequestBody CatchDto catchDto) throws IOException {
        WeatherDto weather = getCurrentWeather(catchDto);
        catchDto.setWeather(null);
        catchDto.setWeather(weather);
        return ResponseEntity.ok(catchLogService.saveCatch(catchDto));
    }

    @GetMapping
    public ResponseEntity<List<CatchDto>> getAllCatchLogs() {
        return ResponseEntity.ok(catchLogService.getAllCatches());
    }
}
