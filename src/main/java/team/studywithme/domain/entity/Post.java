package team.studywithme.domain.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import team.studywithme.api.controller.dto.request.UpdatePostRequest;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "documents")
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(nullable = false)
    private int hits;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public void upHits(){
        this.hits += 1;
    }

    @Builder
    public Post(Avatar avatar, Board board, int hits, String title, String content){
        this.avatar = avatar;
        this.board = board;
        this.hits = hits;
        this.title = title;
        this.content = content;
    }

    public Post(Long postID){
        this.id = postID;
        this.avatar = null;
        this.board = null;
        this.hits = 0;
        this.title = null;
        this.content = null;
    }

    public void updatePost(UpdatePostRequest updatePostRequest){
        this.title = updatePostRequest.getTitle();
        this.content = updatePostRequest.getContent();
    }
}
