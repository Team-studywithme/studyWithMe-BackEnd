package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimerService {

    private final TimerRepository timerRepository;
    private final StudyTimeRepository studyTimeRepository;
    private final AvatarRepository avatarRepository;

    @Transactional
    public MyTimerResponse saveMyTimerStartTime(Long avatarId) {

        Avatar avatar = avatarRepository.findAvatarById(avatarId);

        List<Timer> timers = timerRepository.findTodayTimersByAvatarId(avatarId, LocalDate.now());

        if (timers.size() == 0) {
            timerRepository.save(new Timer(avatar));
            studyTimeRepository.save(new StudyTime(avatar));

            return new MyTimerResponse(LocalTime.of(0, 0, 0));
        }

        LocalTime studyTimeSum = LocalTime.of(0, 0, 0);

        for (Timer timer : timers) {
            if (timer.getEndTime().equals(LocalTime.of(0, 0, 0))) {
                timer.setEndTime(LocalTime.now());
                System.out.println("timer.getEndTIme():" + timer.getEndTime());
            }

            studyTimeSum = studyTimeSum.plus(calculateStudyTime(timer.getStartTime(), timer.getEndTime()));
        }

        timerRepository.save(new Timer(avatar));

        return new MyTimerResponse(studyTimeSum);
    }


    @Transactional
    public MyTimerResponse saveMyTimerEndTime(Long avatarId) {

        Timer findTimer = timerRepository.findTimerByAvatarId(avatarId, LocalDate.now(), LocalTime.of(0, 0, 0));

        if(findTimer == null){
            throw new IllegalArgumentException("정지할 타이머가 존재하지 않습니다.");
        }

        findTimer.setEndTime(LocalTime.now());

        StudyTime studyTime = studyTimeRepository.findStudyTimeByAvatarId(avatarId, LocalDate.now());

        studyTime.addStudyTime(calculateStudyTime(findTimer.getStartTime(), findTimer.getEndTime()));

        LocalTime studyTimeSum = LocalTime.of(0, 0, 0).plus(calculateStudyTime(findTimer.getStartTime(), findTimer.getEndTime()));

        return new MyTimerResponse(studyTimeSum);
    }


    private TemporalAmount calculateStudyTime(LocalTime startTime, LocalTime endTime) {
        return Duration.between(startTime, endTime);
    }


}
