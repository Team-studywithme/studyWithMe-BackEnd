package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.studywithme.api.controller.dto.response.BoardResponse;
import team.studywithme.service.BoardService;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/board")
    public ResponseEntity<BoardResponse> board(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                   @RequestParam(value = "boardName", defaultValue = "matching") String boardName){

        BoardResponse boardResponse = boardService.matchingBoard(pageable, boardName);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponse);
    }
}
