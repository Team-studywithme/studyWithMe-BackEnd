package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team.studywithme.api.controller.dto.response.MyTimerResponse;
import team.studywithme.api.controller.dto.response.StudyingAvatarListResponse;
import team.studywithme.api.controller.dto.response.TempResponse;
import team.studywithme.config.session.LoginAvatarId;
import team.studywithme.initdata.SetTimerData;
import team.studywithme.service.StudyTimeService;
import team.studywithme.service.TimerService;

@Controller
@RequestMapping("/api/timer")
@RequiredArgsConstructor
public class TimerController {


    private final TimerService timerService;
    private final StudyTimeService studyTimeService;
    private final SetTimerData setTimerData;

    @GetMapping("/startTimer")
    public ResponseEntity<MyTimerResponse> startStudyTime(@LoginAvatarId Long avatarId){
        return ResponseEntity.ok(timerService.saveMyTimerStartTime(avatarId));
    }

    @GetMapping("/quitTimer")
    public ResponseEntity<MyTimerResponse> endStudyTime(@LoginAvatarId Long avatarId){
        return ResponseEntity.ok(timerService.saveMyTimerEndTime(avatarId));
    }

    @GetMapping("/studyingUserList")
    public ResponseEntity<StudyingAvatarListResponse> listUsers(){
        return ResponseEntity.ok(studyTimeService.studyingAvatarList());
    }

    @GetMapping("/initTimerData")
    public ResponseEntity<TempResponse> initData(){
        return ResponseEntity.ok(setTimerData.initTimerData());
    }

    @GetMapping("/deleteTimerData")
    public ResponseEntity<TempResponse> deleteData(){
        return ResponseEntity.ok(setTimerData.deleteApp());
    }

}
