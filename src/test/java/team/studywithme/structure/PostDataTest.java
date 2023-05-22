package team.studywithme.structure;

import org.springframework.beans.factory.annotation.Autowired;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Comment;
import team.studywithme.domain.entity.Post;
import team.studywithme.repository.CommentRepository;

import java.util.List;

public class PostDataTest extends BoardDataTest {

    @Autowired
    public CommentRepository commentRepository;

    public Comment makeComment(Avatar avatar, Post post){
        return commentRepository.saveAndFlush(new Comment(avatar, post, "content_1"));
    }

    public List<Comment> makeCommentList(Avatar avatar, Post post){
        return commentRepository.saveAllAndFlush(List.of(
                new Comment(avatar, post, "content_1"),
                new Comment(avatar, post, "content_2"),
                new Comment(avatar, post, "content_3"),
                new Comment(avatar, post, "content_4"),
                new Comment(avatar, post, "content_5"),
                new Comment(avatar, post, "content_6")
        ));
    }

    public List<Comment> makeLittleCommentList(Avatar avatar, Post post){
        return commentRepository.saveAllAndFlush(List.of(
                new Comment(avatar, post, "content_1"),
                new Comment(avatar, post, "content_2"),
                new Comment(avatar, post, "content_3"),
                new Comment(avatar, post, "content_4"),
                new Comment(avatar, post, "content_5"),
                new Comment(avatar, post, "content_6")
        ));
    }

    @Override
    public void deleteAllRepository(){
        commentRepository.deleteAll();
        postRepository.deleteAll();
        boardRepository.deleteAll();
        accountRepository.deleteAll();
        avatarRepository.deleteAll();
    }
}