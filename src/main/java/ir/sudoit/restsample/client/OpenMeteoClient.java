package ir.sudoit.restsample.client;


import ir.sudoit.restsample.dto.LocationDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openMeteoClient", url = "https://api.open-meteo.com/v1")
public interface OpenMeteoClient {

    @GetMapping("/forecast")
    ResponseEntity<LocationDataDto> getForecast(
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude,
            @RequestParam(value = "timezone") String timezone,
            @RequestParam("forecast_days") Integer forecastDays
    );
    
}
