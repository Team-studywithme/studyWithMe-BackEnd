package team.studywithme.repository.elasticsearch;

import org.springframework.data.domain.Pageable;
import team.studywithme.domain.entity.Post;

import java.util.List;

public interface CustomPostSearchRepository {
    List<Post> findPostsByKeywordContains(Pageable pageable, String keyword, Long boardID);
}