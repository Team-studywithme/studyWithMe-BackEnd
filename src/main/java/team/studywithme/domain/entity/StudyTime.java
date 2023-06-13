package team.studywithme.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyTime extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @Column(nullable = false)
    private LocalDate studyDate;

    @Column(nullable = false)
    private LocalTime studyTime;

    public StudyTime(Avatar avatar) {
        this.avatar = avatar;
        this.studyDate = LocalDate.now();
        this.studyTime = LocalTime.of(0,0,0);
    }

    public void addStudyTime(TemporalAmount addTime){
        this.studyTime = studyTime.plus(addTime);
    }

}
