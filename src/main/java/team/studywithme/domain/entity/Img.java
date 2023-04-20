package team.studywithme.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "img")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Img extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "img_list_id")
    private ImgList imglist;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private String url;

    @Builder
    public Img(ImgList img_list, int size, String url){
        this.size = size;
        this.url = url;
    }
}
