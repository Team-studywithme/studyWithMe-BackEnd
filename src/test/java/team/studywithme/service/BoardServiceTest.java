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
    @DisplayName("매칭게시판을_조회")
    class 매칭게시판을_조회{

        @Test
        @DisplayName("매칭게시판을_조회")
        void 매칭게시판을_조회() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            List<Post> postList = makePostList(avatar, board);

            int page = 0;
            int size = 10;
            String boardName = "matching";

            Post post = null;
            List<PostResponse> postResponseList = new ArrayList<>();
            for(int i=postList.size() - 1;i>=0;i--){
                post = postList.get(i);
                postResponseList.add(new PostResponse(post.getId(), post.getTitle(), 0, post.getCreatedDate(), avatar.getNickname()));
            }

            BoardResponse expect = new BoardResponse(boardName, postResponseList);

            // when
            BoardResponse actual = boardService.matchingBoard(page, size, boardName);

            // then
            Assertions.assertEquals(expect, actual);
        }
    }
}