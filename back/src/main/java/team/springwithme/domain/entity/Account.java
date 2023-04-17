package team.springwithme.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity{

    @Id
    private String id;

    @Column(nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @Builder
    public Account(String id, String email, Avatar avatar){
        this.id = id;
        this.email = email;
        this.avatar = avatar;
    }
}
