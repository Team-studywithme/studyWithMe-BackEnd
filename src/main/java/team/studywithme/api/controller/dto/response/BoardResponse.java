package team.studywithme.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = {"name", "postResponseList"})
public class BoardResponse {

    private String name;

    private List<PostResponse> postResponseList;
}
