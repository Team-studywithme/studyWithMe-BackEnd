package team.studywithme.api.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthenticationExceptionResponse {
    private String message;

    public void setExceptionMessage(String message){
        this.message = message;
    }
}