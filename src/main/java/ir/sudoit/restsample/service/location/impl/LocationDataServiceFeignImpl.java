package ir.sudoit.restsample.service.location.impl;

import ir.sudoit.restsample.client.OpenMeteoClient;
import ir.sudoit.restsample.dto.LocationDataDto;
import ir.sudoit.restsample.service.location.LocationDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationDataServiceFeignImpl implements LocationDataService {

    private final OpenMeteoClient openMeteoClient;

    @Override
    @Transactional (timeout = 30)
    public Optional<LocationDataDto> getLocationData(Double latitude, Double longitude) {
        ResponseEntity<LocationDataDto> forecast = openMeteoClient.getForecast(latitude, longitude, "auto", 1);
        if (forecast.getStatusCode().is2xxSuccessful() && forecast.hasBody()) {
            return Optional.ofNullable(forecast.getBody());
        }
        return Optional.empty();
    }
}
