package team.studywithme.api.controller.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {
    private String kakaoServerId;

    private String email;

    private String nickname;

    public KakaoUserInfoDto(String kakaoServerId, String email) {
        this.kakaoServerId = kakaoServerId;
        this.email = email;
        this.nickname = "테스트 닉네임";
    }
}
