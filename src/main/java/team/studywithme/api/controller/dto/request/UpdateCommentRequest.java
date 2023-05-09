package team.studywithme.api.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCommentRequest {

    @NotNull(message = "변경하려는 댓글의 PK는 필수입니다.")
    private Long comment_id;

    @NotEmpty(message = "댓글의 내용은 빈칸으로 변경하지 못합니다.")
    private String content;
}
