package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.studywithme.api.controller.dto.response.PostDetailResponse;
import team.studywithme.service.PostService;


@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @GetMapping("/post")
    public ResponseEntity<PostDetailResponse> post(@PageableDefault(page = 0, size = 5) Pageable pageable,
                                               @RequestParam(value = "postID") Long postID){

        PostDetailResponse postDetailResponse = postService.detailPost(pageable, postID);
        return ResponseEntity.status(HttpStatus.OK).body(postDetailResponse);
    }
}
