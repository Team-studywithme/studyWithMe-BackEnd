package team.studywithme.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.StudyTime;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudyTimeRepositoryTest {

    @Autowired
    StudyTimeRepository studyTimeRepository;


    @Autowired
    AvatarRepository avatarRepository;


    Avatar avatar1;
    Avatar avatar2;


    @BeforeEach
    public void initData() {
        avatar1 = new Avatar("test1");
        avatar2 = new Avatar("test2");

        avatarRepository.save(avatar1);
        avatarRepository.save(avatar2);
    }

    @Test
    @DisplayName("오늘 자 StudyTime 조회시 데이터베이스에 저장된 StudyTime List를 반환해야 한다.")
    void findStudyTimeByStudyDateTest() throws Exception {
        //given
        StudyTime studyTime1 = new StudyTime(avatar1);
        StudyTime studyTime2 = new StudyTime(avatar2);

        studyTimeRepository.save(studyTime1);
        studyTimeRepository.save(studyTime2);
        //when
        List<StudyTime> studyTimeList = studyTimeRepository.findStudyTimeByStudyDate(LocalDate.now());

        //then
        assertThat(studyTimeList).contains(studyTime1);
        assertThat(studyTimeList).contains(studyTime2);
    }


    @Test
    @DisplayName("Avatar Id와 오늘 날짜로 StudyTime 조회시 해당하는 StudyTime을 반환해야 한다.")
    void findStudyTimeByStudyTimeByAvatarIdAndTodayDateTest() throws Exception {
        //given
        StudyTime studyTime1 = new StudyTime(avatar1);
        studyTimeRepository.save(studyTime1);
        //when
        StudyTime findStudyTime = studyTimeRepository.findStudyTimeByAvatarId(avatar1.getId(), LocalDate.now());

        //then
        assertThat(studyTime1.getId()).isEqualTo(findStudyTime.getId());
    }


}