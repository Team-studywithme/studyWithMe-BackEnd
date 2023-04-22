package team.studywithme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select po from Post po " +
            "where po.board.id = :boardID " +
            "order by po.createdDate desc")
    Page<Post> findPagePosts(Pageable pageable, @Param("boardID") Long boardID);
}
