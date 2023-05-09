package team.studywithme.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Avatar extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    @Builder
    public Avatar(String nickname){
        this.nickname = nickname;
    }

    public Avatar(Long avatarID){
        this.id = avatarID;
        this.nickname = null;
    }
}
