package ir.sudoit.restsample.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table (name = "t_location_data")
@Setter
@Getter
@NoArgsConstructor
public class LocationData {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "c_id")
    private Long id;

    @Column (name = "c_latitude")
    private Double latitude;

    @Column (name = "c_longitude")
    private Double longitude;

    @Column (name = "c_generation_time_ms")
    private Double generationTimeMs;

    @Column (name = "c_utc_offset_seconds")
    private Integer utcOffsetSeconds;

    @Column (name = "c_timezone")
    private String timezone;

    @Column (name = "c_timezone_abbreviation")
    private String timezoneAbbreviation;

    @Column (name = "c_elevation")
    private Double elevation;

    /**
     * Ensures proper management and behavior of entities in Hibernate by providing correct implementations of the equals()
     * and hashCode() methods. These methods are crucial for various aspects including entity identification,
     * collection functionality, caching efficiency, and consistency in distributed environments.
     *
     * <p><b>Entity Identity:</b> Each entity in Hibernate represents a row in the database table. Proper identification
     * and management of entities rely on the equals() and hashCode() methods. Incorrect implementations can lead to
     * issues such as duplicate entries in collections or unexpected behavior during entity queries or updates.
     *
     * <p><b>Collections:</b> Entities are commonly stored in collections like Set or Map. Correct implementation of
     * the equals() method defines the equality criteria for entities, ensuring proper functionality of collections.
     * Similarly, the hashCode() method is essential for efficient object location in collections such as HashMap or HashSet.
     * Incorrect implementations can result in inability to retrieve objects from collections or unexpected duplicates.
     *
     * <p><b>Caching and Performance:</b> Hibernate utilizes caching mechanisms to enhance performance. Proper
     * implementations of equals() and hashCode() are vital for caching efficiency. Accurate implementations enable
     * Hibernate to determine whether an object is already present in the cache, minimizing unnecessary database
     * queries and improving overall performance. Conversely, incorrect implementations may lead to cache misses
     * and degraded performance due to frequent database accesses.
     *
     * <p><b>Consistency in Distributed Environments:</b> In distributed environments where entities may be serialized
     * and transmitted across the network, correct implementations of equals() and hashCode() ensure consistency in
     * object comparison across different JVM instances. This consistency is essential for the proper functioning
     * of distributed caching, messaging, and other communication mechanisms.
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        LocationData that = (LocationData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() :
                getClass().hashCode();
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
