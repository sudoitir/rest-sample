package ir.sudoit.restsample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import ir.sudoit.restsample.entity.LocationData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for {@link LocationData}
 */

@Data
@Schema (description = "DTO representing location data")
public class LocationDataDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 3557870467307623611L;

    @Schema (description = "مختصات عرض جغرافیایی", example = "35.6944")
    @NotNull
    private Double latitude;

    @Schema (description = "مختصات طول جغرافیایی", example = "51.4215")
    @NotNull
    private Double longitude;

    @Schema (description = "مدت زمان تولید این داده ها بر حسب میلی ثانیه", example = "23.45")
    @NotNull
    private Double generationTimeMs;

    @Schema (description = "افست در ثانیه از زمان UTC برای منطقه زمانی مکان", example = "12600")
    @NotNull
    private Integer utcOffsetSeconds;

    @Schema (description = "نام منطقه زمانی برای مکان", example = "Asia/Tehran")
    @NotBlank
    private String timezone;

    @Schema (description = "منطقه زمانی", example = "+0330")
    @NotBlank
    private String timezoneAbbreviation;

    @Schema (description = "ارتفاع محل بر حسب متر", example = "24.5")
    @NotNull
    private Double elevation;


    @JsonProperty ("generationtime_ms")
    public void setGenerationTimeMs(Double generationTimeMs) {
        this.generationTimeMs = generationTimeMs;
    }

    @JsonProperty ("utc_offset_seconds")
    public void setUtcOffsetSeconds(Integer utcOffsetSeconds) {
        this.utcOffsetSeconds = utcOffsetSeconds;
    }

    @JsonProperty ("timezone_abbreviation")
    public void setTimezoneAbbreviation(String timezoneAbbreviation) {
        this.timezoneAbbreviation = timezoneAbbreviation;
    }

    @JsonProperty ("generationTimeMs")
    public Double getGenerationTimeMs() {
        return generationTimeMs;
    }

    @JsonProperty ("utcOffsetSeconds")
    public Integer getUtcOffsetSeconds() {
        return utcOffsetSeconds;
    }

    @JsonProperty ("timezoneAbbreviation")
    public String getTimezoneAbbreviation() {
        return timezoneAbbreviation;
    }
}
