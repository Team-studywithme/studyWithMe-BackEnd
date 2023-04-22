package team.studywithme.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.studywithme.domain.entity.Avatar;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoLoginResponse {
    private Long avatarID;

    private String nickname;

    public KakaoLoginResponse AvatarToKakaoLoginResponse(Avatar avatar){
        this.avatarID = avatar.getId();
        this.nickname = avatar.getNickname();

        return this;
    }


}
