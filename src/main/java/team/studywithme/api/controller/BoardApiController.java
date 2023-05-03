package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<BoardResponse> board(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                               @RequestParam(value = "boardName", defaultValue = "matching") String boardName){

        BoardResponse boardResponse = boardService.matchingBoard(page, size, boardName);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponse);
    }
}
