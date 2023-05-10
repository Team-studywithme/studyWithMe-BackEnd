package team.studywithme.structure;

import org.springframework.beans.factory.annotation.Autowired;
import team.studywithme.api.controller.dto.KakaoUserInfoDto;
import team.studywithme.domain.entity.Account;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.repository.AccountRepository;
import team.studywithme.repository.AvatarRepository;

import java.util.List;

public class UserDataTest extends BaseDataTest {

    @Autowired
    public AvatarRepository avatarRepository;

    @Autowired
    public AccountRepository accountRepository;

    public Account makeAccount(Avatar avatar){
        return accountRepository.saveAndFlush(new Account("33445516", "test@gamil.com", avatar));
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

    public KakaoUserInfoDto makeKakaoUserInfo(){
        return new KakaoUserInfoDto("481308431", "test@naver.com");
    }
}