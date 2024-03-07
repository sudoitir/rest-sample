package ir.sudoit.restsample.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "t_location_data")
@Setter
@Getter
@NoArgsConstructor
public class LocationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long id;

    @Column(name = "c_latitude")
    private Double latitude;

    @Column(name = "c_longitude")
    private Double longitude;

    @Column(name = "c_generation_time_ms")
    private Double generationTimeMs;

    @Column(name = "c_utc_offset_seconds")
    private Integer utcOffsetSeconds;

    @Column(name = "c_timezone")
    private String timezone;

    @Column(name = "c_timezone_abbreviation")
    private String timezoneAbbreviation;

    @Column(name = "c_elevation")
    private Double elevation;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        LocationData that = (LocationData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
               "id = " + id + ", " +
               "latitude = " + latitude + ", " +
               "longitude = " + longitude + ", " +
               "generationTimeMs = " + generationTimeMs + ", " +
               "utcOffsetSeconds = " + utcOffsetSeconds + ", " +
               "timezone = " + timezone + ", " +
               "timezoneAbbreviation = " + timezoneAbbreviation + ", " +
               "elevation = " + elevation + ")";
    }
}
