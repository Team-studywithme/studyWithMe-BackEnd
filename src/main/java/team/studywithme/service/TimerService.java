package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.studywithme.api.controller.dto.response.MyTimerResponse;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Timer;
import team.studywithme.repository.AvatarRepository;
import team.studywithme.repository.TimerRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimerService {

    private final TimerRepository timerRepository;
    private final AvatarRepository avatarRepository;


    public MyTimerResponse saveStartTime(Long avatarId){
        Avatar avatar = avatarRepository.findAvatarById(avatarId);
        Timer timer = new Timer(avatar);

        List<Timer> todayTimers = timerRepository.findTodayTimersByAvatarId(avatarId, LocalDate.now());

        timerRepository.save(timer);

        if(todayTimers.size() == 0){
            return new MyTimerResponse(LocalTime.of(0,0,0));
        }

        LocalTime studyTimeSum = LocalTime.of(0,0,0);

        for(Timer t : todayTimers){
            if(t.getEndTime() == null){
                continue;
            }

            studyTimeSum =  studyTimeSum.plus(calculateStudyTime(t.getStartTime(), t.getEndTime()));
        }

        return new MyTimerResponse(studyTimeSum);
    }

    public MyTimerResponse saveEndTime(Long avatarId){
        Timer findTimer = timerRepository.findTimerByAvatarId(avatarId, LocalDate.now());

        findTimer.setEndTime(LocalTime.now());

        LocalTime studyTimeSum = LocalTime.of(0,0,0);

        studyTimeSum =  studyTimeSum.plus(calculateStudyTime(findTimer.getStartTime(), findTimer.getEndTime()));


        return new MyTimerResponse(studyTimeSum);
    }

    private TemporalAmount calculateStudyTime(LocalTime startTime, LocalTime endTime){
        return Duration.between(endTime,startTime);
    }





}
