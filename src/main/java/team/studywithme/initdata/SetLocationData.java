package team.studywithme.initdata;

import lombok.RequiredArgsConstructor;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Location;
import team.studywithme.repository.AvatarRepository;
import team.studywithme.repository.LocationRepository;

@RequiredArgsConstructor
public class SetLocationData {

    private final LocationRepository locationRepository;
    private final AvatarRepository avatarRepository;

    public void data(double lat,double lng){
        System.out.println("==============데이터 초기화 시작=================");
        Avatar avatar1 = new Avatar("avatar1");
        Avatar avatar2 = new Avatar("avatar2");
        Avatar avatar3 = new Avatar("avatar3");
        Avatar avatar4 = new Avatar("avatar5");
        Avatar avatar5 = new Avatar("avatar6");
        Avatar avatar6 = new Avatar("avatar7");
        Avatar avatar7 = new Avatar("avatar8");
        Avatar avatar8 = new Avatar("avatar9");

        Double basicLatitude = lat;
        Double basicLongitude = lng;

        double locationDiffConst = 0.0003;

        avatarRepository.save(avatar1);
        avatarRepository.save(avatar2);
        avatarRepository.save(avatar3);
        avatarRepository.save(avatar4);
        avatarRepository.save(avatar5);
        avatarRepository.save(avatar6);
        avatarRepository.save(avatar7);
        avatarRepository.save(avatar8);

        Location location1 = new Location(avatar1,basicLatitude+locationDiffConst,basicLongitude);
        Location location2 = new Location(avatar2,basicLatitude,basicLongitude+locationDiffConst);
        Location location3 = new Location(avatar3,basicLatitude - locationDiffConst,basicLongitude);
        Location location4 = new Location(avatar4,basicLatitude,basicLongitude - locationDiffConst);
        Location location5 = new Location(avatar5,basicLatitude+locationDiffConst*10,basicLongitude);
        Location location6 = new Location(avatar6,basicLatitude,basicLongitude+locationDiffConst*10);
        Location location7 = new Location(avatar7,basicLatitude - locationDiffConst*10,basicLongitude);
        Location location8 = new Location(avatar8,basicLatitude,basicLongitude - locationDiffConst*10);

        locationRepository.save(location1);
        locationRepository.save(location2);
        locationRepository.save(location3);
        locationRepository.save(location4);
        locationRepository.save(location5);
        locationRepository.save(location6);
        locationRepository.save(location7);
        locationRepository.save(location8);
        System.out.println("==============데이터 끝=================");
    }
}
