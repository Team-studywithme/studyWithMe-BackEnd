package team.studywithme.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Timer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @Column(nullable = false)
    private LocalDateTime start_time;

    @Column(nullable = false)
    private LocalDateTime end_time;

    @Column(nullable = true)
    private LocalDateTime study_time;

    @Builder
    public Timer(Long id, Avatar avatar){
        this.id = id;
        this.avatar = avatar;

    }
}
