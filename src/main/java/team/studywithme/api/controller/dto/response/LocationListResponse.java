package team.studywithme.api.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class LocationListResponse {

    private int count;

    private List<LocationResponse> locationResponses;

    public LocationListResponse(int count, List<LocationResponse> locationResponses) {
        this.count = count;
        this.locationResponses = locationResponses;
    }

}
