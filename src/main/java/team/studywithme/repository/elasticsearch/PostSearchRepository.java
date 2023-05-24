package team.studywithme.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import team.studywithme.domain.entity.Post;

public interface PostSearchRepository extends ElasticsearchRepository<Post, Long>, CustomPostSearchRepository{

}