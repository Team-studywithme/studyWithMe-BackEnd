package team.studywithme.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.response.BoardResponse;
import team.studywithme.api.controller.dto.response.PostResponse;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.domain.entity.Post;
import team.studywithme.structure.PostDataTest;

import java.util.ArrayList;
import java.util.List;


class BoardServiceTest extends PostDataTest {

    @Autowired
    private BoardService boardService;

    @AfterEach
    public void afterSetup(){
        deleteAllRepository();
    }

    @Nested
    @DisplayName("[Service][Board] 매칭게시판을 조회")
    class 매칭게시판을_조회{

        @Test
        @DisplayName("[Service][Board] 매칭게시판을 조회 성공 테스트")
        void 매칭게시판을_조회_성공_테스트() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            List<Post> postList = makePostList(avatar, board);

            int page = 0;
            String boardName = "matching";

            Post post = null;
            List<PostResponse> postResponseList = new ArrayList<>();
            for(int i=postList.size() - 1;i>=0;i--){
                post = postList.get(i);
                postResponseList.add(new PostResponse(post.getId(), post.getTitle(), 0,
                        post.getCreatedDate(), avatar.getId(), avatar.getNickname()));
            }

            BoardResponse expect = new BoardResponse(boardName, Math.round((float) postList.size() / post_size), postResponseList);

            // when
            BoardResponse actual = boardService.matchingBoard(page, boardName);

            // then
            Assertions.assertEquals(expect, actual);
        }
    }

    @Nested
    @DisplayName("[Service][Board] 내 게시판을 조회")
    class 내_게시판을_조회{

        @Test
        @Transactional
        @DisplayName("[Service][Board] 내 게시판을 조회 성공 테스트")
        void 내_게시판을_조회_성공_테스트() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            List<Post> postList = makePostList(avatar, board);

            int page = 0;
            String boardName = "matching";

            Post post = null;
            List<PostResponse> postResponseList = new ArrayList<>();
            for(int i=postList.size() - 1;i>=0;i--){
                post = postList.get(i);
                postResponseList.add(new PostResponse(post.getId(), post.getTitle(), 0,
                        post.getCreatedDate(), avatar.getId(), avatar.getNickname()));
            }

            BoardResponse expect = new BoardResponse(boardName, Math.round((float) postList.size() / post_size), postResponseList);

            // when
            BoardResponse actual = boardService.matchingMyBoard(page, avatar.getId(), boardName);

            // then
            Assertions.assertEquals(expect, actual);
        }
    }

    @Nested
    @DisplayName("[Service][Board] 게시판을 검색")
    class 게시판을_검색{

        @Test
        @Transactional
        @DisplayName("[Service][Board] 게시판을 검색 성공 테스트")
        void 게시판을_검색_성공_테스트() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            List<Post> postList = makePostList(avatar, board);

            int page = 0;
            String keyword = "le";
            String boardName = "matching";

            Post post = null;
            List<PostResponse> postResponseList = new ArrayList<>();
            for(int i=postList.size() - 1;i>=0;i--){
                post = postList.get(i);
                postResponseList.add(new PostResponse(post.getId(), post.getTitle(), 0,
                        post.getCreatedDate(), avatar.getId(), avatar.getNickname()));
            }

            BoardResponse expect = new BoardResponse(boardName, Math.round((float) postList.size() / post_size), postResponseList);

            // when
            BoardResponse actual = boardService.matchingSearchBoard(page, keyword, boardName);

            // then
            Assertions.assertEquals(expect, actual);
        }
    }

    @Nested
    @DisplayName("[Service][Board] 게시물 리스트로 게시판 응답 만들기")
    class 게시물_리스트로_게시판_응답_만들기{

        @Test
        @Transactional
        @DisplayName("[Service][Board] 게시물 리스트로 게시판 응답 만들기 성공 테스트")
        void 게시물_리스트로_게시판_응답_만들기_성공_테스트() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            List<Post> postList = makePostList(avatar, board);

            String boardName = "matching";

            Post post = null;
            List<PostResponse> postResponseList = new ArrayList<>();
            for(int i=0;i<postList.size();i++){
                post = postList.get(i);
                postResponseList.add(new PostResponse(post.getId(), post.getTitle(), 0,
                        post.getCreatedDate(), avatar.getId(), avatar.getNickname()));
            }

            BoardResponse expect = new BoardResponse(boardName, Math.round((float) postList.size() / post_size), postResponseList);

            // when
            BoardResponse actual = boardService.postPageToBoardResponse(board, Math.round((float) postList.size() / post_size), postList);

            // then
            Assertions.assertEquals(expect, actual);
        }
    }
}