package team.studywithme.structure;

import org.springframework.beans.factory.annotation.Autowired;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Comment;
import team.studywithme.domain.entity.Post;
import team.studywithme.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

public class PostDataTest extends BoardDataTest {

    @Autowired
    public CommentRepository commentRepository;

    public Comment makeComment(Avatar avatar, Post post){
        return commentRepository.saveAndFlush(new Comment(avatar, post, "content_1"));
    }

    public List<Comment> makeCommentList(Avatar avatar, Post post){
        List<Comment> commentList = new ArrayList<>();

        for(int i=0;i<6;i++){
            commentList.add(commentRepository.saveAndFlush(new Comment(avatar, post, "content" + i)));
        }

        return commentList;
    }

    public List<Comment> makeLittleCommentList(Avatar avatar, Post post){
        return commentRepository.saveAllAndFlush(List.of(
                new Comment(avatar, post, "content_1")
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