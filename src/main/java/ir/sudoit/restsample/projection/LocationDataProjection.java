package ir.sudoit.restsample.projection;

public interface LocationDataProjection {
    Double getLatitude();
    Double getLongitude();
    Double getGenerationTimeMs();
    Integer getUtcOffsetSeconds();
    String getTimezone();
    String getTimezoneAbbreviation();
    Double getElevation();
}
