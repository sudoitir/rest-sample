package ir.sudoit.restsample.service.location;

import ir.sudoit.restsample.dto.LocationDataDto;

import java.util.Optional;

public interface LocationDataService {

    Optional<LocationDataDto> getLocationData(Double latitude, Double longitude);

}
