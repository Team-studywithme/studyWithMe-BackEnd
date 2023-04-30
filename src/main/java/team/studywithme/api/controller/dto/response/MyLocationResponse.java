package team.studywithme.api.controller.dto.response;

import lombok.Getter;

@Getter
public class MyLocationResponse {

    private String name;
    private Double latitude;
    private Double longitude;

    public MyLocationResponse(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
