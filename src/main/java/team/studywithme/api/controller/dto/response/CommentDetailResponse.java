package team.studywithme.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = {"id", "content", "avatarID", "nickname"})
public class CommentDetailResponse {
    private Long id;
    private String content;
    private Long avatarID;
    private String nickname;
}
