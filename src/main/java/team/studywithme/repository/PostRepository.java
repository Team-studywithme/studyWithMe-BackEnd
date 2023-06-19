package team.studywithme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select po from Post po " +
            "where po.board.id = :boardID and po.active = 1 " +
            "order by po.createdDate desc")
    Page<Post> findPagePosts(Pageable pageable, @Param("boardID") Long boardID);

    @Query(value = "select po from Post po " +
            "where po.board.id = :boardID and po.active = 1 and po.avatar.id = :avatarID " +
            "order by po.createdDate desc")
    Page<Post> findMyPagePosts(Pageable pageable, @Param("avatarID") Long avatarID, @Param("boardID") Long boardID);

    @Query(value = "select po from Post po " +
            "where po.board.id = :boardID and po.active = 1 and po.title LIKE %:keyword% " +
            "order by po.createdDate desc")
    Page<Post> findSearchPagePosts(Pageable pageable, @Param("keyword") String keyword, @Param("boardID") Long boardID);

    @Query(value = "select po from Post po " +
            "where po.id = :postID and po.active = 1")
    Post findPostById(@Param("postID") Long postID);

    @Modifying
    @Query("update Post po set po.active = 0 where po.avatar.id = :avatarID")
    int updateActives(@Param("avatarID") Long avatarID);
}
