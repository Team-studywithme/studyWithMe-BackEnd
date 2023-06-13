package team.studywithme.api.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TempResponse {

    private String message;

    public TempResponse(String message) {
        this.message = message;
    }
}
