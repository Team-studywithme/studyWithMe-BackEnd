package team.studywithme.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = {"id", "title", "createdDate", "avatarID", "nickname", "content", "hits", "commentDetailResponseList", "next"})
public class PostDetailResponse {


    private Long id;
    private String title;
    private LocalDateTime createdDate;
    private Long avatarID;
    private String nickname;
    private String content;
    private int hits;
    private List<CommentDetailResponse> commentDetailResponseList;
    private boolean next;

    public PostDetailResponse postCommentToPostDetailResponse(Post post, Avatar avatar, List<CommentDetailResponse> commentDetailResponseList, boolean hasNext){
        this.id = post.getId();
        this.title = post.getTitle();
        this.createdDate = post.getCreatedDate();
        this.hits = post.getHits();
        this.avatarID = post.getAvatar().getId();
        this.nickname = avatar.getNickname();
        this.content = post.getContent();
        this.commentDetailResponseList = commentDetailResponseList;
        this.next = hasNext;

        return this;
    }
}
