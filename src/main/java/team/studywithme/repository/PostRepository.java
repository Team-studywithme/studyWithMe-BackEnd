package team.studywithme.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select po from Post po " +
            "where po.board.id = :boardID and active = 1 " +
            "order by po.createdDate desc")
    List<Post> findPagePosts(Pageable pageable, @Param("boardID") Long boardID);

}
