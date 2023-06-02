package team.studywithme.service;

import org.junit.jupiter.api.*;
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
import team.studywithme.structure.PostDataTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class PostServiceTest extends PostDataTest {

    @Autowired
    private PostService postService;

    @AfterEach
    public void afterSetup(){
        deleteAllRepository();
    }

    @Nested
    @DisplayName("[Service][Post] 게시물 상세정보 조회")
    class 게시물_상세정보_조회{

        @Test
        @DisplayName("[Service][Post] 게시물 상세정보 조회 성공 테스트 - 댓글이 더있을때")
        void 게시물_상세정보_조회_성공_테스트_댓글이_더있을때() {
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
        @DisplayName("[Service][Post] 게시물 상세정보 조회 성공 테스트 - 댓글이 더없을때")
        void 게시물_상세정보_조회_성공_테스트_댓글이_더없을때() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);
            List<Comment> commentList = makeLittleCommentList(avatar, post);

            int page = 0;

            Comment comment = null;
            List<CommentDetailResponse> commentDetailResponseList = new ArrayList<>();
            for(int i=0;i<1;i++){
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
    @DisplayName("[Service][Post] 게시물 생성")
    class 게시물_생성 {

        @Test
        @DisplayName("[Service][Post] 게시물 생성 성공 테스트")
        void 게시물_생성_성공_테스트() {
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
    @DisplayName("[Service][Post] 게시물 업데이트")
    class 게시물_업데이트 {

        @Test
        @DisplayName("[Service][Post] 게시물 업데이트 성공 테스트")
        void 게시물_업데이트_성공_테스트() {
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
    @DisplayName("[Service][Post] 게시물 삭제")
    class 게시물_삭제 {

        @Test
        @DisplayName("[Service][Post] 게시물 삭제 성공 테스트")
        void 게시물_삭제_성공_테스트() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            // when
            postService.deletePost(post.getId(), avatar.getId());

            Post actual = postRepository.findPostById(post.getId());

            // then
            Assertions.assertNull(actual);
        }
    }

    @Nested
    @DisplayName("[Service][Post] Avatar 리스트 HashMap 변환")
    class Avatar_리스트_HashMap_변환 {

        @Test
        @DisplayName("[Service][Post] Avatar 리스트 HashMap 변환 성공 테스트")
        void Avatar_리스트_HashMap_변환_성공_테스트() {
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
