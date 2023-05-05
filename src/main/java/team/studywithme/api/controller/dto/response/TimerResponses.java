package team.studywithme.api.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TimerResponses {

    List<TimerResponse> timerResponses;

    public TimerResponses(boolean hasNext, List<TimerResponse> timerResponses) {
        this.timerResponses = timerResponses;
    }
}
