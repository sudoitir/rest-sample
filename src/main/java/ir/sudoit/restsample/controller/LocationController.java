package ir.sudoit.restsample.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.sudoit.restsample.dto.LocationDataDto;
import ir.sudoit.restsample.service.location.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/locations")
@RequiredArgsConstructor
@Tag (name = "Location Controller", description = "${location.controller.title}")
public class LocationController {

    // @Qualifier("locationFeignServiceImpl") for notice
    private final LocationService locationRestTemplateServiceImpl;


    @GetMapping ("location/{id}")
    @Operation (summary = "${location.controller.get.single.summary}",
                description = "${location.controller.get.single.description}")
    @ApiResponse (responseCode = "200", description = "${location.controller.get.single.response.200.description}",
                  content = @Content (mediaType = "application/json",
                                      schema = @Schema (implementation = LocationDataDto.class)))
    @ApiResponse (responseCode = "404", description = "${location.controller.get.single.response.404.description}",
                  content = @Content)
    public ResponseEntity<LocationDataDto> getLocation(
            @Parameter (description = "${location.controller.get.parameter.id.description}", required = true,
                        example = "1") @PathVariable Long id) {
        return ResponseEntity.ok().body(locationRestTemplateServiceImpl.getLocationDate(id));
    }

    @GetMapping ("all")
    @Operation (summary = "${location.controller.get.all.summary}",
                description = "${location.controller.get.all.description}")
    public ResponseEntity<List<LocationDataDto>> getAllLocationData() {
        return ResponseEntity.ok().body(locationRestTemplateServiceImpl.getAllLocationDate());
    }

    @PutMapping ("update")
    @Operation (summary = "${location.controller.update.summary}",
                description = "${location.controller.update.description}")
    @ApiResponse (responseCode = "204", description = "${location.controller.update.response.204.description}")
    @ApiResponse (responseCode = "400", description = "${location.controller.update.response.400.description}",
                  content = @Content)
    public ResponseEntity<Void> updateLocationData(
            @Parameter (description = "${location.controller.update.latitude.description}", required = true,
                        example = "51.5072") @RequestParam @NonNull Double latitude,
            @Parameter (description = "${location.controller.update.longitude.description}", required = true,
                        example = "-0.1275") @RequestParam @NonNull Double longitude) {
        locationRestTemplateServiceImpl.updateLocationData(latitude, longitude);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping ("custom-create")
    @Operation (summary = "${location.controller.create.summary}",
                description = "${location.controller.create.description}")
    @io.swagger.v3.oas.annotations.parameters.RequestBody (
            description = "${location.controller.create.request.body.description}",
            required = true, content = @Content (schema = @Schema (implementation = LocationDataDto.class)))
    @ApiResponse (responseCode = "201", description = "${location.controller.create.response.201.description}",
                  content = @Content (schema = @Schema (type = "string", example = "OK")))
    @ApiResponse (responseCode = "400", description = "${location.controller.create.response.400.description}",
                  content = @Content)
    public ResponseEntity<String> createCustomLocationData(@RequestBody LocationDataDto locationDataDto) {
        locationRestTemplateServiceImpl.create(locationDataDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }

}
