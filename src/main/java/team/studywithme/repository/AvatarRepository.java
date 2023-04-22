package team.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
