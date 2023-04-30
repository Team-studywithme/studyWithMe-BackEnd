package team.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.Location;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

    @Query("select lo from Location lo " +
            "where lo.latitude " +
            "between :latitudeleft and :latituderight " +
            "and lo.longitude " +
            "between :longitudeleft and :longituderight and lo.active = 1")
    List<Location> findLocationsByCoordinate(@Param("latitudeleft") double targetLatitudeLeft,
                                             @Param("longitudeleft") double targetLongitudeLeft,
                                             @Param("latituderight") double targetLatitudeRight,
                                             @Param("longituderight") double targetLongitudeRight);
}
