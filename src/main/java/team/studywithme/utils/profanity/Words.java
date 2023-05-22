package team.studywithme.utils.profanity;


import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Words {
    private List<String> badWords;

    public Words(){
        badWords = new ArrayList<>(Arrays.asList(
                "ㅅㅂ", "시발", "씨발"
        ));
    }
}
