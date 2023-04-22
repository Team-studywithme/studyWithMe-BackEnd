package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team.studywithme.api.controller.dto.response.BoardResponse;
import team.studywithme.api.controller.dto.response.PostResponse;
import team.studywithme.domain.entity.Board;
import team.studywithme.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PostService postService;

    public BoardResponse matchingBoard(Pageable pageable, String boardName){
        Board board = boardRepository.findBoardByName(boardName);

        List<PostResponse> postResponsePage =  postService.findPostResponseListByPageable(pageable, board.getId());
        return new BoardResponse(board.getName(), postResponsePage);
    }
}
