package team.studywithme.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = {"id", "title", "createdDate", "nickname"})
public class PostResponse {

    private Long id;
    private String title;
    private int hits;
    private LocalDateTime createdDate;
    private String nickname;
}
