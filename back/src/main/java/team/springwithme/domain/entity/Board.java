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
@Table(name = "board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @CreationTimestamp
    private LocalDateTime created_date = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime modified_date = LocalDateTime.now();

    @Column(nullable = false)
    @ColumnDefault("1")
    private int active;

    @Builder
    public Board(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
