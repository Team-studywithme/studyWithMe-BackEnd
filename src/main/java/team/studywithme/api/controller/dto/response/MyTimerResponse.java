package team.studywithme.api.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class MyTimerResponse {

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime studyTime;

    public MyTimerResponse(LocalTime studyTime) {
        this.studyTime = studyTime;
    }
}
