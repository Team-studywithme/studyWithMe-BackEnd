package team.studywithme.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Comment;
import team.studywithme.domain.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailResponse {


    private Long id;
    private String title;
    private LocalDateTime createdDate;
    private String nickname;
    private String content;
    private List<CommentDetailResponse> commentDetailResponseList;
    private boolean isNext;

    public PostDetailResponse postCommentToPostDetailResponse(Post post, Avatar avatar, List<CommentDetailResponse> commentDetailResponseList, boolean hasNext){
        this.id = post.getId();
        this.title = post.getTitle();
        this.createdDate = post.getCreatedDate();
        this.nickname = avatar.getNickname();
        this.content = post.getContent();
        this.commentDetailResponseList = commentDetailResponseList;
        this.isNext = hasNext;

        return this;
    }
}
