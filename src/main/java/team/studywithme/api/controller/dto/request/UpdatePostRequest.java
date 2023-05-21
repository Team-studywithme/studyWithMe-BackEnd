package team.studywithme.api.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostRequest {

    @NotNull(message = "변경하려는 게시물의 PK는 필수입니다.")
    private Long post_id;

    @NotEmpty(message = "게시물의 제목은 빈칸으로 변경하지 못합니다.")
    private String title;

    @NotEmpty(message = "게시물의 내용은 빈칸으로 변경하지 못합니다.")
    private String content;

    public void setElement(String title, String content){
        this.title = title;
        this.content = content;
    }
}
