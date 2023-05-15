package team.studywithme.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.request.PostRequest;
import team.studywithme.api.controller.dto.request.UpdatePostRequest;
import team.studywithme.api.controller.dto.response.CommentDetailResponse;
import team.studywithme.api.controller.dto.response.PostDetailResponse;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.domain.entity.Comment;
import team.studywithme.domain.entity.Post;
import team.studywithme.service.structure.PostDataServiceTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class PostServiceTest extends PostDataServiceTest {

    @Autowired
    private PostService postService;

    @Nested
    @DisplayName("게시물_상세정보_조회")
    class 게시물_상세정보_조회{

        @Test
        @Transactional
        @DisplayName("게시물_상세정보_조회_댓글이_더있을때")
        void 게시물_상세정보_조회_댓글이_더있을때() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);
            List<Comment> commentList = makeCommentList(avatar, post);


            int page = 0;

            Comment comment = null;
            List<CommentDetailResponse> commentDetailResponseList = new ArrayList<>();
            for(int i=commentList.size() - 1;i>=1;i--){
                comment = commentList.get(i);
                commentDetailResponseList.add(new CommentDetailResponse(
                        comment.getId(), comment.getContent(), avatar.getId(), avatar.getNickname()
                ));
            }

            PostDetailResponse expect = new PostDetailResponse(
                    post.getId(), post.getTitle(), post.getCreatedDate(), post.getAvatar().getId(),
                    avatar.getNickname(), post.getContent(), 1, commentDetailResponseList, true
            );

            // when
            PostDetailResponse actual = postService.detailPost(page, post.getId());

            // then
            Assertions.assertEquals(expect, actual);
        }

        @Test
        @Transactional
        @DisplayName("게시물_상세정보_조회_댓글이_더없을때")
        void 게시물_상세정보_조회_댓글이_더없을때() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);
            List<Comment> commentList = makeCommentList(avatar, post);

            int page = 1;

            Comment comment = null;
            List<CommentDetailResponse> commentDetailResponseList = new ArrayList<>();
            for(int i=commentList.size() - 3;i>=0;i--){
                comment = commentList.get(i);
                commentDetailResponseList.add(new CommentDetailResponse(
                        comment.getId(), comment.getContent(), avatar.getId(), avatar.getNickname()
                ));
            }

            PostDetailResponse expect = new PostDetailResponse(
                    post.getId(), post.getTitle(), post.getCreatedDate(), post.getAvatar().getId(),
                    avatar.getNickname(), post.getContent(), 1, commentDetailResponseList, false
            );

            // when
            PostDetailResponse actual = postService.detailPost(page, post.getId());

            // then
            Assertions.assertEquals(expect, actual);
        }
    }

    @Nested
    @DisplayName("게시물_생성")
    class 게시물_생성 {

        @Test
        @Transactional
        @DisplayName("게시물_생성_성공")
        void 게시물_생성_성공() {
            // given
            String title = "example_title_test";
            String content = "example_content_test";

            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();

            PostRequest postRequest = new PostRequest(board.getId(), title, content);

            Post expect = new Post(avatar, board, 0, title, content);

            // when
            Post actual = postService.createPost(postRequest, avatar.getId());

            // then
            Assertions.assertEquals(expect.getTitle(), actual.getTitle());
            Assertions.assertEquals(expect.getHits(), actual.getHits());
            Assertions.assertEquals(expect.getContent(), actual.getContent());
            Assertions.assertEquals(expect.getAvatar().getId(), actual.getAvatar().getId());
            Assertions.assertEquals(expect.getBoard().getId(), actual.getBoard().getId());
        }
    }

    @Nested
    @DisplayName("게시물_업데이트")
    class 게시물_업데이트 {

        @Test
        @Transactional
        @DisplayName("게시물_업데이트_성공")
        void 게시물_업데이트_성공() {
            // given
            String title = "example_title_test";
            String content = "update_content_test";

            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            Post expect = new Post(avatar, board, 0, title, content);

            UpdatePostRequest updatePostRequest = new UpdatePostRequest(post.getId(), title, content);

            // when
            Post actual = postService.updatePost(updatePostRequest, avatar.getId());

            // then
            Assertions.assertEquals(expect.getTitle(), actual.getTitle());
            Assertions.assertEquals(expect.getHits(), actual.getHits());
            Assertions.assertEquals(expect.getContent(), actual.getContent());
            Assertions.assertEquals(expect.getAvatar().getId(), actual.getAvatar().getId());
            Assertions.assertEquals(expect.getBoard().getId(), actual.getBoard().getId());
        }
    }

    @Nested
    @DisplayName("게시물_삭제")
    class 게시물_삭제 {

        @Test
        @Transactional
        @DisplayName("게시물_삭제_성공")
        void 게시물_삭제_성공() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            // when
            postService.deletePost(post.getId(), avatar.getId());

            // then
            Assertions.assertEquals(0, post.getActive());
        }
    }

    @Nested
    @DisplayName("Avatar_리스트_HashMap_변환")
    class Avatar_리스트_HashMap_변환 {

        @Test
        @Transactional
        @DisplayName("Avatar_리스트_HashMap_변환")
        void Avatar_리스트_HashMap_변환() {
            // given
            List<Avatar> avatarList = List.of(
                    new Avatar("example_1"),
                    new Avatar("example_2"),
                    new Avatar("example_3")
            );

            HashMap<Long, String> expect = new HashMap<>();

            for(Avatar avatar : avatarList) {
                expect.put(avatar.getId(), avatar.getNickname());
            }

            // when
            HashMap<Long, String> actual = postService.ListToHashMapForNickname(avatarList);

            Assertions.assertEquals(expect, actual);
        }
    }
}
