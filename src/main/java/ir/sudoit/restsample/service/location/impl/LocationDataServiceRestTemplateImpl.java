package ir.sudoit.restsample.service.location.impl;

import ir.sudoit.restsample.dto.LocationDataDto;
import ir.sudoit.restsample.service.location.LocationDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationDataServiceRestTemplateImpl implements LocationDataService {

    private final RestTemplate restTemplate;

    @Override
    public Optional<LocationDataDto> getLocationData(Double latitude, Double longitude) {
        String url = "https://api.open-meteo.com/v1/forecast"
                + "?latitude=" + latitude
                + "&longitude=" + longitude
                + "&timezone=" + "auto"
                + "&forecast_days=" + 1;

        ResponseEntity<LocationDataDto> response = restTemplate.getForEntity(url, LocationDataDto.class);
        if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
            return Optional.ofNullable(response.getBody());
        }
        return Optional.empty();
    }
}
