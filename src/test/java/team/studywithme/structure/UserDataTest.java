package team.studywithme.structure;

import org.springframework.beans.factory.annotation.Autowired;
import team.studywithme.api.controller.dto.KakaoUserInfoDto;
import team.studywithme.domain.entity.Account;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.repository.AccountRepository;
import team.studywithme.repository.AvatarRepository;
import team.studywithme.utils.kakao.KakaoAuthorizationInfo;

import java.util.List;
import java.util.UUID;

public class UserDataTest extends BaseDataTest {

    @Autowired
    public AvatarRepository avatarRepository;

    @Autowired
    public AccountRepository accountRepository;

    public Account makeAccount(Avatar avatar){
        return accountRepository.saveAndFlush(new Account(UUID.randomUUID().toString().substring(0, 8), "test@gamil.com", avatar));
    }

    public Account makeKakaoAccount(Avatar avatar){
        return accountRepository.saveAndFlush(new Account(KakaoAuthorizationInfo.SUCCESS_KAKAO, "test@gamil.com", avatar));
    }

    public Avatar makeAvatar(){
        return avatarRepository.save(new Avatar("Example_A"));
    }

    public List<Avatar> makeAvatarList(){
        return avatarRepository.saveAllAndFlush(List.of(
                new Avatar("Example_A"),
                new Avatar("Example_B"),
                new Avatar("Example_C")
        ));
    }

    public void deleteAllRepository(){
        accountRepository.deleteAll();
        avatarRepository.deleteAll();
    }
}