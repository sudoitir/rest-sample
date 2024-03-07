package ir.sudoit.restsample.service.location.impl;

import ir.sudoit.restsample.dto.LocationDataDto;
import ir.sudoit.restsample.entity.LocationData;
import ir.sudoit.restsample.projection.LocationDataProjection;
import ir.sudoit.restsample.repository.LocationDataRepository;
import ir.sudoit.restsample.service.location.LocationDataService;
import ir.sudoit.restsample.service.location.LocationService;
import ir.sudoit.restsample.util.mapper.LocationDataMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationDataRepository locationDataRepository;
    private final LocationDataMapper locationDataMapper;
    //@Qualifier("locationDataServiceFeignImpl") for notice!
    private final LocationDataService locationDataServiceFeignImpl;


    @Override
    @Transactional (timeout = 30)
    public Long updateLocationData(Double latitude, Double longitude) {
        Optional<LocationDataDto> locationData = locationDataServiceFeignImpl.getLocationData(latitude, longitude);
        LocationData locationDataEntity = locationData.map(locationDataMapper::toEntity)
                .orElseThrow(() -> new EntityNotFoundException("Data not received!"));
        return locationDataRepository.saveAndFlush(locationDataEntity).getId();
    }

    @Override
    @Transactional (readOnly = true)
    public List<LocationDataDto> getAllLocationDate() {
        List<LocationDataProjection> allData = locationDataRepository.findAllBy();
        return allData.stream()
                .map(locationDataMapper::toDto)
                .toList();
    }

    @Override
    @Transactional (readOnly = true)
    public LocationDataDto getLocationDate(Long id) {
        return locationDataRepository.findById(id)
                .map(locationDataMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Data not found!"));
    }

    @Override
    @Transactional
    public void create(LocationDataDto locationDataDto) {
        LocationData entity = locationDataMapper.toEntity(locationDataDto);
        locationDataRepository.save(entity);
    }
}
