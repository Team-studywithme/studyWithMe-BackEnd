package team.studywithme.api.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyLocationRequest {

    private double latitude;
    private double longitude;

    public MyLocationRequest(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
