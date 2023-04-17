package team.springwithme.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "friend_list")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend_List extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Avatar my_id;

    @Column(nullable = false)
    private Long friend_id;

    @Column(nullable = false)
    private int accept;

    @Builder
    public Friend_List(Long id, Avatar my_id, Long friend_id, int accept) {
        this.id = id;
        this.my_id = my_id;
        this.friend_id = friend_id;
        this.accept = accept;
    }
}
