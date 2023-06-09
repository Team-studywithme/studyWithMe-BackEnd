package team.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select bo from Board bo where bo.name = :name and bo.active = 1")
    Board findBoardByName(@Param("name") String name);

    @Query("select bo from Board bo where bo.id = :board_id and bo.active = 1")
    Board findBoardById(@Param("board_id") Long board_id);
}
