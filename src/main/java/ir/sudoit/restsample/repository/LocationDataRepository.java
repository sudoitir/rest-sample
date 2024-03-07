package ir.sudoit.restsample.repository;

import ir.sudoit.restsample.entity.LocationData;
import ir.sudoit.restsample.projection.LocationDataProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * {@link JpaSpecificationExecutor} offers a powerful mechanism for building and executing dynamic queries against JPA
 * entities in a type-safe manner, thereby enhancing the flexibility and maintainability of your data access layer.
 */
public interface LocationDataRepository
        extends JpaRepository<LocationData, Long>, JpaSpecificationExecutor<LocationData> {

    List<LocationDataProjection> findByTimezoneLikeIgnoreCaseOrderByGenerationTimeMsDesc(@NonNull String timezone);

    List<LocationDataProjection> findAllBy();
}
