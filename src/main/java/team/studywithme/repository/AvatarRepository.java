package team.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.Avatar;

import java.util.List;
import java.util.Set;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    @Query("select av from Avatar av where av.id in :idList")
    List<Avatar> findByIdList(@Param("idList") Set<Long> idSet);

    @Query("select av from Avatar av " +
            "where id = :id")
    Avatar findAvatarById(@Param("id") Long id);
}
