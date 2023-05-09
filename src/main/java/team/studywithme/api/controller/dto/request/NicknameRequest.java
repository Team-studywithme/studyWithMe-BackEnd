package team.studywithme.api.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NicknameRequest {

    @NotEmpty(message = "닉네임 입력은 필수 입니다.")
    @Size(min =  2, max = 8, message = "아이디는 최소 2자이상 8자 이하입니다.")
    private String nickname;
}
