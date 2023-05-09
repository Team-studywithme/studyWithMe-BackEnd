package team.studywithme.domain.entity;

import lombok.*;
import team.studywithme.api.controller.dto.request.UpdateCommentRequest;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String content;

    @Builder
    public Comment(Avatar avatar, Post post, String content){
        this.avatar = avatar;
        this.post = post;
        this.content = content;
    }

    public void updateComment(UpdateCommentRequest updateCommentRequest){
        this.content = updateCommentRequest.getContent();
    }
}
