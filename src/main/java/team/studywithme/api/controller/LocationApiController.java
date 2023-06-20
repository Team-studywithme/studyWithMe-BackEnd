package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.studywithme.api.controller.dto.request.MyLocationRequest;
import team.studywithme.api.controller.dto.response.LocationListResponse;
import team.studywithme.api.controller.dto.response.MyLocationResponse;
import team.studywithme.config.session.LoginAvatarId;
import team.studywithme.service.LocationService;

@RestController
@RequiredArgsConstructor
public class LocationApiController {

    private final LocationService locationService;

    @PostMapping ("/location/save")
    public ResponseEntity<MyLocationResponse> saveLocation(@RequestBody MyLocationRequest myLocationRequest,
                                                           @LoginAvatarId Long avatarId){
        return ResponseEntity.ok(locationService.saveMyLocation(
                myLocationRequest.getLatitude(),
                myLocationRequest.getLongitude(),
                avatarId));
    }

    @GetMapping("/location/call")
    public ResponseEntity<LocationListResponse> callNearAvatar(@RequestParam Double latitude,
                                                               @RequestParam Double longitude,
                                                               @RequestParam Integer distance){
        return ResponseEntity.ok(locationService.findNearAvatar(latitude,longitude,distance));
    }

    @GetMapping("/location/initdata")
    public ResponseEntity<String> initData(@RequestParam Double latitude,
                                           @RequestParam Double longitude){
        return ResponseEntity.ok(locationService.initData(latitude,longitude));
    }
}
