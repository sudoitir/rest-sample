package ir.sudoit.restsample.util.mapper;

import ir.sudoit.restsample.dto.LocationDataDto;
import ir.sudoit.restsample.entity.LocationData;
import ir.sudoit.restsample.projection.LocationDataProjection;
import org.mapstruct.*;

@Mapper (unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationDataMapper {

    LocationData toEntity(LocationDataDto locationDataDto);

    LocationDataDto toDto(LocationData locationData);
    LocationDataDto toDto(LocationDataProjection locationData);

    @BeanMapping (nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LocationData partialUpdate(
            LocationDataDto locationDataDto, @MappingTarget LocationData locationData);

}