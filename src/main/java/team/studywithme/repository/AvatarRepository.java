package team.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;https://github.com/Team-studywithme/studyWithMe-BackEnd/pull/31/conflict?name=src%252Fmain%252Fjava%252Fteam%252Fstudywithme%252Frepository%252FAvatarRepository.java&ancestor_oid=6a95f844e9cd097dddc1699529429ccadfc8226a&base_oid=6f4290f1fc974c51fcd8a089127efac7762bb45d&head_oid=0fa4f1695f1ab3e703af9eb445ba2ce3a89545ce
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

    @Query("select av from Avatar av where av.id = :id and av.active = 1")
    Avatar findAvatarById(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("update Avatar av set av.nickname = :nickname where av.id = :id")
    int updateNickname(@Param("id") Long id, @Param("nickname") String nickname);
}
