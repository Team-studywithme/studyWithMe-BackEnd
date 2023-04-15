package team.springwithme.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    private LocalDateTime created_date = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime modified_date = LocalDateTime.now();

    @Column(nullable = false)
    @ColumnDefault("1")
    private int active;

    @Builder
    public Post(Long id, Avatar avatar, Board board, String title, String content){
        this.id = id;
        this.avatar = avatar;
        this.board = board;
        this.title = title;
        this.content = content;
    }
}
