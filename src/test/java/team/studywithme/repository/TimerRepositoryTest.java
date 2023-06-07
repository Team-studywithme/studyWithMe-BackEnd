package team.studywithme.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Timer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TimerRepositoryTest {

    @Autowired
    TimerRepository timerRepository;


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
    @DisplayName("입력한 유저의 오늘자 타이머 기록을 모두 가져온다.")
    public void findTimersTest1() {
        for (int i = 0; i < 10; i++) {
            timerRepository.save(new Timer(avatar1));
        }

        List<Timer> findList = timerRepository.findTodayTimersByAvatarId(avatar1.getId(), LocalDate.now());

        assertThat(findList.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("입력한 유저의 타이머 중 아직 종료시간이 00:00:00인 타이머를 가져온다.")
    public void findTimerTest() {
        Timer timer1 = new Timer(avatar1);
        Timer timer2 = new Timer(avatar1);

        timer2.setEndTime(LocalTime.now().plusHours(1));

        timerRepository.save(timer1);
        timerRepository.save(timer2);

        Timer findTimer = timerRepository.findTimerByAvatarId(avatar1.getId(), LocalDate.now(), LocalTime.of(0, 0, 0));

        assertThat(findTimer.getId()).isEqualTo(timer1.getId());
    }


}