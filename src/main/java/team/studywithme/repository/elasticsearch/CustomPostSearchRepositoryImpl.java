package team.studywithme.repository.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import team.studywithme.domain.entity.Post;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class CustomPostSearchRepositoryImpl implements CustomPostSearchRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<Post> findPostsByKeywordContains(Pageable pageable, String keyword, Long boardID) {
        Criteria criteria = Criteria.where("title").contains(keyword)
                .or(Criteria.where("content").contains(keyword))
                .and(String.valueOf(Criteria.where("board.id").equals(boardID)));
        Query query = new CriteriaQuery(criteria).setPageable(pageable);
        SearchHits<Post> search = elasticsearchOperations.search(query, Post.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}