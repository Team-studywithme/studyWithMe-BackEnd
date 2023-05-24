package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.response.BoardResponse;
import team.studywithme.api.controller.dto.response.PostResponse;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.domain.entity.Post;
import team.studywithme.repository.AvatarRepository;
import team.studywithme.repository.BoardRepository;
import team.studywithme.repository.PostRepository;
import team.studywithme.repository.elasticsearch.PostSearchRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final int post_size = 5;

    private final AvatarRepository avatarRepository;
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final PostSearchRepository postSearchRepository;

    public BoardResponse matchingBoard(int page, String boardName){
        Board board = boardRepository.findBoardByName(boardName);

        List<Post> postList = postRepository.findPagePosts(PageRequest.of(page, post_size), board.getId());

        return postListToBoardResponse(board, postList);
    }

    public BoardResponse matchingMyBoard(int page, Long avatarID, String boardName){
        Board board = boardRepository.findBoardByName(boardName);

        List<Post> postList = postRepository.findMyPagePosts(PageRequest.of(page, post_size), avatarID, board.getId());

        return postListToBoardResponse(board, postList);
    }

    public BoardResponse matchingSearchBoard(int page, String keyword, String boardName){
        Board board = boardRepository.findBoardByName(boardName);

        List<Post> postList = postRepository.findSearchPagePosts(PageRequest.of(page, post_size), keyword, board.getId());

        return postListToBoardResponse(board, postList);
    }

    public BoardResponse matchingElasticSearchBoard(int page, String keyword, String boardName){
        Board board = boardRepository.findBoardByName(boardName);

        List<Post> postList = postSearchRepository.findPostsByKeywordContains(PageRequest.of(page, post_size), keyword, board.getId());

        return postListToBoardResponse(board, postList);
    }

    public BoardResponse postListToBoardResponse(Board board, List<Post> postList){
        Set<Long> idSet = postList.stream().map(post -> post.getAvatar().getId()).collect(Collectors.toSet());
        List<Avatar> avatarList = avatarRepository.findByIdList(idSet);

        HashMap<Long, String> avatarMap = ListToHashMapForNickname(avatarList);

        List<PostResponse> postResponsePage =  postList.stream().map(post -> new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getHits(),
                post.getCreatedDate(),
                post.getAvatar().getId(),
                avatarMap.get(post.getAvatar().getId()))).collect(Collectors.toList());

        return new BoardResponse(board.getName(), postResponsePage);
    }

    public HashMap<Long, String> ListToHashMapForNickname(List<Avatar> avatarList){
        HashMap<Long, String> hashMap = new HashMap<>();

        for(Avatar avatar : avatarList){
            hashMap.put(avatar.getId(), avatar.getNickname());
        }

        return hashMap;
    }
}
