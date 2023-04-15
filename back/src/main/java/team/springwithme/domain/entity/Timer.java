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
@Table(name = "timer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Timer {

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

    @Column(nullable = false)
    private LocalDateTime study_time;

    @CreationTimestamp
    private LocalDateTime created_date = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime modified_date = LocalDateTime.now();

    @Column(nullable = false)
    @ColumnDefault("1")
    private int active;

    @Builder
    public Timer(Long id, Avatar avatar, LocalDateTime start_time, LocalDateTime end_time, LocalDateTime study_time){
        this.id = id;
        this.avatar = avatar;
        this.start_time = start_time;
        this.end_time = end_time;
        this.study_time = study_time;
    }
}
