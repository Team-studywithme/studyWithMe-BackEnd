package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.initdata.SetLocationData;
import team.studywithme.api.controller.dto.response.LocationListResponse;
import team.studywithme.api.controller.dto.response.LocationResponse;
import team.studywithme.api.controller.dto.response.MyLocationResponse;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Location;
import team.studywithme.repository.AvatarRepository;
import team.studywithme.repository.LocationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final AvatarRepository avatarRepository;

    @Transactional
    public MyLocationResponse saveMyLocation(Double latitude, Double longitude,Long avatarId){

        Avatar findAvatar = avatarRepository.findAvatarById(avatarId);

        if(findAvatar == null){
            throw new IllegalArgumentException("존재 하지 않는 회원 입니다.");
        }

        avatarRepository.save(findAvatar);
        locationRepository.save(new Location(findAvatar, latitude, longitude));

        return new MyLocationResponse("test1",latitude,longitude);
    }

    public LocationListResponse findNearAvatar(Double latitude,Double longitude,Integer distance){

        Double distanceGoal = 0.0001 * distance;

        List<Location> locationList = locationRepository.findLocationsByCoordinate(
                latitude - distanceGoal, longitude - distanceGoal,
                latitude + distanceGoal, longitude + distanceGoal
        );

        List<LocationResponse> collect = locationList.stream()
                .map(location ->
                    new LocationResponse(
                            location.getAvatar().getNickname(),
                            location.getLatitude(),
                            location.getLongitude(),
                            location.getCreatedDate()))
                .collect(Collectors.toList());

        int count = (int) locationList.stream().count();

        return new LocationListResponse(count ,collect);
    }


    @Transactional
    public String initData(Double latitude, Double longitude){
        SetLocationData setLocationData = new SetLocationData(locationRepository, avatarRepository);
        setLocationData.data(latitude,longitude);

        return "데이터 초기화 완료";
    }


}
