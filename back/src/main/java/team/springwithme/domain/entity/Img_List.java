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
@Table(name = "img_list")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Img_List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @CreationTimestamp
    private LocalDateTime created_date = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime modified_date = LocalDateTime.now();

    @Column(nullable = false)
    @ColumnDefault("1")
    private int active;

    @Builder
    public Img_List(Long id, Post post){
        this.id = id;
        this.post = post;
    }
}
