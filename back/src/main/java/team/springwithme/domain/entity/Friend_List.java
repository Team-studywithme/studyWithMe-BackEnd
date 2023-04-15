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
@Table(name = "friend_list")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend_List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Avatar my_id;

    @Column(nullable = false)
    private Long friend_id;

    @Column(nullable = false)
    private int accept;

    @CreationTimestamp
    private LocalDateTime created_date = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime modified_date = LocalDateTime.now();

    @Column(nullable = false)
    @ColumnDefault("1")
    private int active;

    @Builder
    public Friend_List(Long id, Avatar my_id, Long friend_id, int accept){
        this.id = id;
        this.my_id = my_id;
        this.friend_id = friend_id;
        this.accept = accept;
    }
}
