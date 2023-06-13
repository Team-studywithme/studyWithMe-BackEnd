package team.studywithme.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import team.studywithme.api.controller.dto.response.MyTimerResponse;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.StudyTime;
import team.studywithme.domain.entity.Timer;
import team.studywithme.repository.AvatarRepository;
import team.studywithme.repository.StudyTimeRepository;
import team.studywithme.repository.TimerRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Profile("test")
@SpringBootTest
class TimerServiceTest {

    @Autowired
    TimerService timerService;

    @Autowired
    TimerRepository timerRepository;

    @Autowired
    AvatarRepository avatarRepository;

    @Autowired
    StudyTimeRepository studyTimeRepository;

    Avatar avatar1;


    @BeforeEach
    void init() {
        avatar1 = new Avatar("test");
        avatarRepository.save(avatar1);
    }

    @AfterEach
    void destroy() {
        timerRepository.deleteAll();
        studyTimeRepository.deleteAll();
        avatarRepository.deleteAll();
    }


    @Test
    @DisplayName("타이머 시작시에 오늘자 타이머가 존재하지 않는 경우 response의 값은 00:00:00이여야 한다.")
    void saveMyTimerStartTime() {
        MyTimerResponse expectResponse = new MyTimerResponse(LocalTime.of(0, 0, 0));
        MyTimerResponse response = timerService.saveMyTimerStartTime(avatar1.getId());

        assertThat(response.getStudyTime()).isEqualTo(expectResponse.getStudyTime());
    }

    @Test
    @DisplayName("타이머 시작시에 작동하고 있는 타이머가 존재하는 경우 response의 값은 기존 타이머를 종료시키고 응답 값에는 공부시간을 되돌려 주어야 한다.")
    void saveMyTimerStartTimeExistAnotherActiveTimer() throws Exception {
        //given
        StudyTime studyTime = new StudyTime(avatar1);
        studyTimeRepository.save(studyTime);

        Timer timer = new Timer(avatar1);
        timerRepository.save(timer);

        Thread.sleep(1000);

        //when
        MyTimerResponse response = timerService.saveMyTimerStartTime(avatar1.getId());
        List<Timer> timers = timerRepository.findTodayTimersByAvatarId(avatar1.getId(), LocalDate.now());
        timer = timers.get(0);
        //then
        LocalTime expectedTime = LocalTime.of(0, 0, 0).plus(calculateStudyTime(timer.getStartTime(), timer.getEndTime()));
        assertThat(response.getStudyTime()).isEqualTo(expectedTime);

        assertThat(timer.getEndTime()).isAfter(LocalTime.of(0, 0, 0));

    }

    TemporalAmount calculateStudyTime(LocalTime startTime, LocalTime endTime) {
        return Duration.between(startTime, endTime);
    }

    @Test
    @DisplayName("작동하고 있는 타이머의 타이머 종료버튼을 누르면 타이머가 종료되고 해당 타이머의 공부시간을 response 값으로 내려준다.")
    void saveMyTimerEndTimeActiveTimer() throws Exception {
        //given
        StudyTime studyTime = new StudyTime(avatar1);
        studyTimeRepository.save(studyTime);

        Timer timer = new Timer(avatar1);
        timerRepository.save(timer);

        Thread.sleep(1000);
        //when
        MyTimerResponse myTimerResponse = timerService.saveMyTimerEndTime(avatar1.getId());
        List<Timer> timers = timerRepository.findTodayTimersByAvatarId(avatar1.getId(), LocalDate.now());
        timer = timers.get(0);
        LocalTime expectedTime = LocalTime.of(0, 0, 0).plus(calculateStudyTime(timer.getStartTime(), timer.getEndTime()));


        //then
        assertThat(myTimerResponse.getStudyTime()).isEqualTo(expectedTime);
        assertThat(timer.getEndTime()).isAfter(LocalTime.of(0, 0, 0));
    }

    @Test
    @DisplayName("만약 작동하고 있는 타이머가 없는 경우 예외를 발생시킨다.")
    void saveMyTimerEndTimeNotExistActiveTimer() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> timerService.saveMyTimerEndTime(avatar1.getId()));
    }


}