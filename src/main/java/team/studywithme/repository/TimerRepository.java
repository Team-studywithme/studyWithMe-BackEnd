package team.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.Timer;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimerRepository extends JpaRepository<Timer,Long> {

    @Query("select t from Timer t join fetch Avatar a where t.active = 1 and t.studyDate = :localDate")
    List<Timer> findTodayTimers(LocalDate localDate);

    @Query("select t from Timer t where t.active = 1 and t.studyDate = :localDate and t.avatar.id = :avatarId")
    List<Timer> findTodayTimersByAvatarId(Long avatarId, LocalDate localDate);

    @Query("select t from Timer t where t.active = 1 " +
            "and t.studyDate = :locaDate " +
            "and t.avatar.id = :avatarId " +
            "order by t.startTime desc " +
            "limit 1")
    Timer findTimerByAvatarId(Long avatarId, LocalDate localDate);
}
