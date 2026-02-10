package com.simas.FishingHelper.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simas.FishingHelper.Model.Dtos.CatchDto;
import com.simas.FishingHelper.Utilities.ParameterStringBuilder;
import com.simas.FishingHelper.Model.Dtos.WeatherDto;
import io.github.cdimascio.dotenv.Dotenv;
import org.shredzone.commons.suncalc.MoonIllumination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.simas.FishingHelper.Utilities.HttpUtilities.createUrl;

@Service
public class WeatherService {

    static Logger logger = LoggerFactory.getLogger(WeatherService.class);

    public static WeatherDto getCurrentWeather(CatchDto catchDto) throws IOException {
        final Dotenv dotenv = Dotenv.configure().load();
        final String api_key = dotenv.get("WEATHER_API_KEY");
        final URL api_url = createUrl(dotenv.get("WEATHER_API_URL"));

        Map<String, String> params = new HashMap<>();
        params.put("appid", api_key);
        params.put("lat", catchDto.getLat());
        params.put("lon", catchDto.getLon());
        params.put("units", "metric");

        String queryString = ParameterStringBuilder.getParamsString(params);

        URL request_url = new URL(api_url + "?" + queryString);
        logger.info("Calling OpenWeather API: " +  request_url);

        StringBuilder response = new StringBuilder();

        HttpURLConnection con = (HttpURLConnection) request_url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("GET");

        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(params));
        out.flush();
        out.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }

        in.close();
        con.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseJson = mapper.readTree(response.toString());
        double moonPhase = calculateMoonPhase(catchDto.getDateTime());

        WeatherDto builtWeatherDto = new WeatherDto(responseJson.path("weather").path(0).path("description").asText(),
                responseJson.path("main").path("temp").asDouble(),
                responseJson.path("wind").path("speed").asDouble(),
                responseJson.path("main").path("pressure").asInt(),
                moonPhase);
        logger.info("Received data: " + builtWeatherDto);

        return builtWeatherDto;
    }

    static double calculateMoonPhase(LocalDateTime currentTime) {
        MoonIllumination illumination = MoonIllumination.compute().on(currentTime).execute();

        return illumination.getFraction();
    }
}
