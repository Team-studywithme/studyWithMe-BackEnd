package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.studywithme.api.controller.dto.response.MyTimerResponse;
import team.studywithme.api.controller.dto.response.StudyingAvatarListResponse;
import team.studywithme.config.session.LoginAvatarId;
import team.studywithme.service.StudyTimeService;
import team.studywithme.service.TimerService;

@RestController
@RequiredArgsConstructor
public class TimerController {


    private final TimerService timerService;
    private final StudyTimeService studyTimeService;

    @GetMapping("/timer/startTimer")
    public ResponseEntity<MyTimerResponse> startStudyTime(@LoginAvatarId final Long avatarId){
        return ResponseEntity.ok(timerService.saveMyTimerStartTime(avatarId));
    }

    @GetMapping("/timer/quitTimer")
    public ResponseEntity<MyTimerResponse> endStudyTime(@LoginAvatarId final Long avatarId){
        return ResponseEntity.ok(timerService.saveMyTimerEndTime(avatarId));
    }

    @GetMapping("/timer/studyingUserList")
    public ResponseEntity<StudyingAvatarListResponse> listUsers(){
        return ResponseEntity.ok(studyTimeService.studyingAvatarList());
    }
}
