package team.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.StudyTime;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudyTimeRepository extends JpaRepository<StudyTime,Long> {

    @Query("select st from StudyTime st " +
            "join fetch st.avatar " +
            "where st.studyDate = :studyDate " +
            "and st.active = 1")
    List<StudyTime> findStudyTimeByStudyDate(@Param("studyDate") LocalDate studyDate);

    @Query("select st from StudyTime st where st.avatar.id = :avatarId and st.studyDate = :studyDate")
    StudyTime findStudyTimeByAvatarId(@Param("avatarId")Long avatarId,@Param("studyDate") LocalDate localDate);

}
