package team.studywithme.api.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class MyTimerResponse {

    private LocalTime studyTime;

    public MyTimerResponse(LocalTime studyTime) {
        this.studyTime = studyTime;
    }
}
