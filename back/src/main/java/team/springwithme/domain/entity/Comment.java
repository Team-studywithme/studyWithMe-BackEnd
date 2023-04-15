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
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

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
    public Comment(Long id, Avatar avatar, Post post, String content){
        this.id = id;
        this.avatar = avatar;
        this.post = post;
        this.content = content;
    }
}
