package com.simas.FishingHelper.Controller;

import com.simas.FishingHelper.CatchLog;
import com.simas.FishingHelper.Service.CatchLogService;
import com.simas.FishingHelper.WeatherLog;
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
    private final CatchLogService service;

    @PostMapping
    public ResponseEntity<CatchLog> logCatch(@RequestBody CatchLog catchLog) throws IOException {
        WeatherLog weather = getCurrentWeather(catchLog.getLat(), catchLog.getLon());
        catchLog.setWeather(null);
        catchLog.setWeather(weather);
        return ResponseEntity.ok(service.save(catchLog));
    }

    @GetMapping
    public ResponseEntity<List<CatchLog>> getAllCatchLogs() {
        return ResponseEntity.ok(service.getAll());
    }
}
