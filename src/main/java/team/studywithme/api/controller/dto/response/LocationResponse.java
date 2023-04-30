package team.studywithme.api.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LocationResponse {

    private String nickname;
    private Double latitude;
    private Double longitude;
    private LocalDateTime startTime;

    public LocationResponse(String nickname, Double latitude, Double longitude, LocalDateTime localDateTime) {
        this.nickname = nickname;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startTime = localDateTime;
    }
}
