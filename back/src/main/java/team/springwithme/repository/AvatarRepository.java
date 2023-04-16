package team.springwithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.springwithme.domain.entity.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
