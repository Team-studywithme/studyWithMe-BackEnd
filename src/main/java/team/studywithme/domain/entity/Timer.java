package team.studywithme.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Timer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @Column(nullable = false)
    private LocalDate studyDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    public Timer(Avatar avatar){
        this.avatar = avatar;
        this.studyDate = LocalDate.now();
        this.startTime = LocalTime.now().withNano(0);
        this.endTime = LocalTime.of(0,0,0).withNano(0);

    }

    public void setEndTime(LocalTime endTime){
        this.endTime = endTime.withNano(0);
    }

}
