package team.studywithme.api.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CommentRequest {

    @NotNull(message = "게시물 PK는 필수입니다.")
    private Long post_id;

    @NotEmpty(message = "게시물의 내용은 필수입니다.")
    private String content;
}
