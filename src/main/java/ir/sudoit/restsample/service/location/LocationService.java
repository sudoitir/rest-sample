package ir.sudoit.restsample.service.location;

import ir.sudoit.restsample.dto.LocationDataDto;

import java.util.List;

public interface LocationService {

    Long updateLocationData(Double latitude, Double longitude);

    List<LocationDataDto> getAllLocationDate();

    LocationDataDto getLocationDate(Long id);

    void create(LocationDataDto locationDataDto);
}
