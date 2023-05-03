package team.studywithme.api.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PostRequest {

    @NotNull(message = "게시판 PK는 필수입니다.")
    private Long board_id;

    @NotEmpty(message = "게시판의 제목은 필수입니다.")
    private String title;

    @NotEmpty(message = "게시판의 내용은 필수입니다.")
    private String content;
}

