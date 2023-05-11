package team.studywithme.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select co from Comment co " +
            "where co.post.id = :postID and co.active = 1 " +
            "order by co.createdDate desc")
    Slice<Comment> findSliceComments(Pageable pageable, @Param("postID") Long postID);

    @Query(value = "select co from Comment co " +
            "where co.id = :commentID and co.active = 1")
    Comment findCommentById(@Param("commentID") Long commentID);

    @Modifying
    @Query(value = "update Comment co set co.active = 0 where co.post.id = :postID ")
    int deleteByPost(@Param("postID") Long postID);

    @Modifying
    @Query("update Comment co set co.active = 0 where co.avatar.id = :avatarID")
    int updateActives(@Param("avatarID") Long avatarID);
}
