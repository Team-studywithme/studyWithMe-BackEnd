package team.studywithme.structure;

import org.springframework.beans.factory.annotation.Autowired;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.domain.entity.Post;
import team.studywithme.repository.BoardRepository;
import team.studywithme.repository.PostRepository;

import java.util.List;

public class BoardDataTest extends UserDataTest {

    @Autowired
    public BoardRepository boardRepository;
    @Autowired
    public PostRepository postRepository;

    public Board makeBoard(){
        return boardRepository.save(new Board("matching"));
    }

    public Post makePost(Avatar avatar, Board board){
        return postRepository.saveAndFlush(new Post(avatar, board, 0, "title_A", "content_A"));
    }

    public List<Post> makePostList(Avatar avatar, Board board){
        return postRepository.saveAllAndFlush(List.of(
                new Post(avatar, board, 0, "title_A", "content_A"),
                new Post(avatar, board, 0, "title_B", "content_B"),
                new Post(avatar, board, 0, "title_C", "content_C")
        ));
    }
}