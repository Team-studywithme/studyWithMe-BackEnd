package team.studywithme.api.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class StudyingUserResponse {

    private String username;

    private LocalTime localTime;

    public StudyingUserResponse(String username, LocalTime localTime) {
        this.username = username;
        this.localTime = localTime;
    }
}
