package team.studywithme.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.response.BoardResponse;
import team.studywithme.api.controller.dto.response.PostResponse;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.domain.entity.Post;
import team.studywithme.service.structure.PostDataServiceTest;

import java.util.ArrayList;
import java.util.List;


class BoardServiceTest extends PostDataServiceTest {

    @Autowired
    private BoardService boardService;

    @Nested
    @DisplayName("매칭게시판을_조회")
    class 매칭게시판을_조회{

        @Test
        @Transactional
        @DisplayName("매칭게시판을_조회")
        void 매칭게시판을_조회() {
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

            BoardResponse expect = new BoardResponse(boardName, postResponseList);

            // when
            BoardResponse actual = boardService.matchingBoard(page, boardName);

            // then
            Assertions.assertEquals(expect, actual);
        }
    }

    @Nested
    @DisplayName("내_게시판을_조회")
    class 내_게시판을_조회{

        @Test
        @Transactional
        @DisplayName("내_게시판을_조회")
        void 내_게시판을_조회() {
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

            BoardResponse expect = new BoardResponse(boardName, postResponseList);

            // when
            BoardResponse actual = boardService.matchingMyBoard(page, avatar.getId(), boardName);

            // then
            Assertions.assertEquals(expect, actual);
        }
    }

    @Nested
    @DisplayName("게시판을_검색")
    class 게시판을_검색{

        @Test
        @Transactional
        @DisplayName("게시판을_검색")
        void 게시판을_검색() {
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

            BoardResponse expect = new BoardResponse(boardName, postResponseList);

            // when
            BoardResponse actual = boardService.matchingSearchBoard(page, keyword, boardName);

            // then
            Assertions.assertEquals(expect, actual);
        }
    }

    @Nested
    @DisplayName("게시물리스트로_게시판응답만들기")
    class 게시물리스트로_게시판응답만들기{

        @Test
        @Transactional
        @DisplayName("게시물리스트로_게시판응답만들기_성공")
        void 게시물리스트로_게시판응답만들기_성공() {
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

            BoardResponse expect = new BoardResponse(boardName, postResponseList);

            // when
            BoardResponse actual = boardService.postListToBoardResponse(board, postList);

            // then
            Assertions.assertEquals(expect, actual);
        }
    }
}