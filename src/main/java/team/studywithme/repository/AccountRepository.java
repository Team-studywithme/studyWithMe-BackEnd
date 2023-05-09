package team.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select ac from Account ac where ac.id = :id")
    Account findAccountByIdNoneIf(@Param("id") String id);

    @Query("select ac from Account ac where ac.avatar.id = :avatarID and ac.active = 1")
    Account findAccountByAvatarID(@Param("avatarID") Long avatarID);
}
