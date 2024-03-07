package ir.sudoit.restsample.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
public class LocationController {

    // @Qualifier("locationFeignServiceImpl") for notice
    private final LocationService locationRestTemplateServiceImpl;

    @GetMapping ("location/{id}")
    @Operation (summary = "دریافت اطلاعات مکان", description = "داده های موقعیت مکانی شناسه داده شده")
    @ApiResponse (responseCode = "200", description = "اطلاعات مکان با موفقیت بازیابی شد",
                  content = @Content (mediaType = "application/json",
                                      schema = @Schema (implementation = LocationDataDto.class)))
    @ApiResponse (responseCode = "404", description = "مکان یافت نشد", content = @Content)
    public ResponseEntity<LocationDataDto> getLocation(
            @Parameter (description = "شناسه محل", required = true, example = "1") @PathVariable Long id) {
        return ResponseEntity.ok().body(locationRestTemplateServiceImpl.getLocationDate(id));
    }

    @GetMapping ("all")
    public ResponseEntity<List<LocationDataDto>> getAllLocationData() {
        return ResponseEntity.ok().body(locationRestTemplateServiceImpl.getAllLocationDate());
    }

    @PutMapping ("update")
    @Operation (summary = "به روز رسانی اطلاعات مکان",
                description = "داده های مکان را با طول و عرض جغرافیایی ارائه شده به روز می کند")
    @ApiResponse (responseCode = "204", description = "اطلاعات مکان با موفقیت به روز شد")
    @ApiResponse (responseCode = "400", description = "پارامترهای درخواست نامعتبر", content = @Content)
    public ResponseEntity<Void> updateLocationData(
            @Parameter (description = "مختصات عرض جغرافیایی مکان", required = true, example = "51.5072")
            @RequestParam @NonNull Double latitude,
            @Parameter (description = "مختصات طول جغرافیایی مکان", required = true, example = "-0.1275")
            @RequestParam @NonNull Double longitude) {
        locationRestTemplateServiceImpl.updateLocationData(latitude, longitude);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping ("custom-create")
    @Operation (summary = "یک مکان جدید ایجاد کنید", description = "با داده های ارائه شده یک مکان جدید ایجاد می کند")
    @io.swagger.v3.oas.annotations.parameters.RequestBody (description = "داده های مکان برای مکان جدید",
                                                           required = true, content = @Content (
            schema = @Schema (implementation = LocationDataDto.class)))
    @ApiResponse (responseCode = "201", description = "مکان با موفقیت ایجاد شد",
                  content = @Content (schema = @Schema (type = "string", example = "OK")))
    @ApiResponse (responseCode = "400", description = "بدنه درخواست نامعتبر است", content = @Content)
    public ResponseEntity<String> updateLocationData(@RequestBody LocationDataDto locationDataDto) {
        locationRestTemplateServiceImpl.create(locationDataDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }

}
