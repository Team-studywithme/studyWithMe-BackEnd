package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team.studywithme.api.controller.dto.response.MyTimerResponse;
import team.studywithme.config.session.LoginAvatarId;
import team.studywithme.service.TimerService;

@Controller
@RequestMapping("/api/timer")
@RequiredArgsConstructor
public class TimerController {


    private final TimerService timerService;

    @GetMapping("/startTimer")
    public ResponseEntity<MyTimerResponse> startStudyTime(@LoginAvatarId Long avatarId){
        return ResponseEntity.ok(timerService.saveStartTime(avatarId));
    }

    @GetMapping("/quitTimer")
    public ResponseEntity<MyTimerResponse> endStudyTime(@LoginAvatarId Long avatarId){
        return ResponseEntity.ok(timerService.saveEndTime(avatarId));
    }

}
