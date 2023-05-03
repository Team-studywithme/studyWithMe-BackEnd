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
            int size = 2;

            Comment comment = null;
            List<CommentDetailResponse> commentDetailResponseList = new ArrayList<>();
            for(int i=commentList.size() - 1;i>=1;i--){
                comment = commentList.get(i);
                commentDetailResponseList.add(new CommentDetailResponse(
                        comment.getId(), comment.getContent(), avatar.getNickname()
                ));
            }

            PostDetailResponse expect = new PostDetailResponse(
                    post.getId(), post.getTitle(), post.getCreatedDate(),
                    avatar.getNickname(), post.getContent(), 1, commentDetailResponseList, true
            );

            // when
            PostDetailResponse actual = postService.detailPost(page, size, post.getId());

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
            int size = 2;

            Comment comment = null;
            List<CommentDetailResponse> commentDetailResponseList = new ArrayList<>();
            for(int i=commentList.size() - 3;i>=0;i--){
                comment = commentList.get(i);
                commentDetailResponseList.add(new CommentDetailResponse(
                        comment.getId(), comment.getContent(), avatar.getNickname()
                ));
            }

            PostDetailResponse expect = new PostDetailResponse(
                    post.getId(), post.getTitle(), post.getCreatedDate(),
                    avatar.getNickname(), post.getContent(), 1, commentDetailResponseList, false
            );

            // when
            PostDetailResponse actual = postService.detailPost(page, size, post.getId());

            // then
            Assertions.assertEquals(expect, actual);
        }
    }

    @Nested
    @DisplayName("게시물_생성")
    class 게시물_생성 {

        @Test
        @Transactional
        @DisplayName("게시물_생성")
        void 게시물_생성() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();

            PostRequest postRequest = new PostRequest(board.getId(), "example_title_test", "example_content_test");

            // when
            postService.createPost(postRequest, avatar.getId());
        }
    }

    @Nested
    @DisplayName("게시물_업데이트")
    class 게시물_업데이트 {

        @Test
        @Transactional
        @DisplayName("게시물_업데이트")
        void 게시물_업데이트() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            UpdatePostRequest updatePostRequest = new UpdatePostRequest(post.getId(), "example_title_test", "example_content_test");

            // when
            postService.updatePost(updatePostRequest, avatar.getId());
        }
    }

    @Nested
    @DisplayName("게시물_삭제")
    class 게시물_삭제 {

        @Test
        @Transactional
        @DisplayName("게시물_삭제")
        void 게시물_삭제() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            // when
            postService.deletePost(post.getId(), avatar.getId());
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
