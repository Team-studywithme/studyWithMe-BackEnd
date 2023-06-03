package team.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.Timer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TimerRepository extends JpaRepository<Timer,Long> {

    @Query("select t from Timer t " +
            "where t.active = 1 " +
            "and t.studyDate = :localDate " +
            "and t.avatar.id = :avatarId")
    List<Timer> findTodayTimersByAvatarId(@Param("avatarId") Long avatarId, @Param("localDate") LocalDate localDate);

    @Query("select t from Timer t " +
            "where t.active = 1 " +
            "and t.studyDate = :localDate " +
            "and t.avatar.id = :avatarId " +
            "and t.endTime = :localTime")
   Timer findTimerByAvatarId(@Param("avatarId") Long avatarId, @Param("localDate") LocalDate localDate, @Param("localTime") LocalTime localTime);

}
